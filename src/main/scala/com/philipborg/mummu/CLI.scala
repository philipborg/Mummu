package com.philipborg.mummu

import org.apache.commons.cli.HelpFormatter
import java.awt.GraphicsEnvironment
import java.nio.charset.Charset
import org.apache.commons.cli.DefaultParser
import org.apache.commons.cli.CommandLineParser
import java.nio.charset.StandardCharsets
import org.apache.commons.cli.Options
import org.apache.commons.cli.CommandLine
import java.nio.file.Paths
import java.nio.file.Path
import java.nio.file.Files
import java.sql.Time
import java.util.concurrent.TimeUnit
import com.philipborg.mummu.io.FileResolver
import scala.io.StdIn

object CLI {
  val CLIOptions: Options = new Options {
    addOption("w", "workspace", true, "The workspace where files are read and written from.");
    addOption("e", "execute", true, "Execute a ECMA/Java-script relative to the current workspace.");
    addOption("g", "gui", false, "Open JavaFX based GUI.");
    addOption("h", "help", false, "Lists all commands.");
    addOption("hl", "headless", false, "Returns whatever the instance is running in a headless environment.");
    //addOption("i", "interactive", false, "Activates the interactive ECMA/Java-script mode.");
    addOption("c", "charset", true, "Charset used to load files.");
    addOption("t", "timelimit", true, "Time limit for JS Script. 0 is infinite.");
    addOption("T", "timeunit", true, "Time unit for timelimit.");
    addOption("e", "exit", false, "Exits the application.");
  }
  val parser: CommandLineParser = new DefaultParser();
}

class CLI {
  def run(args: Array[String]): Unit = {
    if (args.length > 0) {
      interpret(args);
      return ;
    }

    while (true) {
      println("Entering interpreter mode.");
      val line = StdIn.readLine();
      interpret(line.split(" (?=\")|(?<=\")\\s"));
    }
  }

  var workspace: Path = Paths.get(".").toAbsolutePath.normalize;
  var charset: Charset = StandardCharsets.UTF_8;
  var timeunit: TimeUnit = TimeUnit.MINUTES;
  var timelimit: Option[Long] = None;

  protected def interpret(args: Array[String]): Unit = {
    val cml: CommandLine = CLI.parser.parse(CLI.CLIOptions, args);

    if (cml.hasOption("help")) {
      val hf = new HelpFormatter;
      hf.printHelp("mummu", CLI.CLIOptions);
    }
    if (cml.hasOption("charset")) {
      charset = Charset.forName(cml.getOptionValue("charset"));
      println("Set charset to " + charset.displayName);
    }
    if (cml.hasOption("timeunit")) {
      timeunit = TimeUnit.valueOf(cml.getOptionValue("timeunit"));
    }
    if (cml.hasOption("timelimit")) {
      timelimit = Some(timeunit.toMillis(cml.getOptionValue("timelimit").toLong));
      if (timelimit.get == 0) timelimit = None;
    }
    if (cml.hasOption("headless")) {
      println(GraphicsEnvironment.isHeadless)
    }
    if (cml.hasOption("workspace")) {
      val suggestedWS = cml.getOptionValue("workspace");
      val path = Paths.get(suggestedWS);
      val dirPath = Files.createDirectories(path);
      workspace = dirPath;
    }
    if (cml.hasOption("execute")) {
      val code = new String(Files.readAllBytes(workspace.resolve(cml.getOptionValue("execute"))), charset);
      val request = new Request(code = code, pathResolver = new FileResolver(workspace.toAbsolutePath.toString), timelimit);
      val resp = request.call();
      if (resp.isDefined) throw resp.get;
    }
    if (cml.hasOption("gui")) {
      val gui = new SimpleGUI;
      gui.workingDirectory.value = workspace.toAbsolutePath.toString;
      gui.main(Array.empty[String]);
    }
    if (cml.hasOption("exit")) {
      System.exit(0);
    }
  }
}