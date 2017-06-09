package com.philipborg.mummu

import scala.io.StdIn

import com.philipborg.mummu.io.FileResolver
import java.nio.charset.StandardCharsets
import java.io.OutputStreamWriter

object CLI {
  val codeBreaker = "§";
  def main(args: Array[String]) = {

    println("Enter work directory");
    val dir = StdIn.readLine;
    val resolver = new FileResolver(dir);
    while (true) {
      println("Write JS then end a line with a " + codeBreaker);
      var script = "";
      var completed = false;
      while (!completed) {
        val line = StdIn.readLine;
        if (line.equals(codeBreaker)) {
          completed = true;
        } else {
          script += System.lineSeparator + line;
        }
      }
      val writer = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
      val task = new Request(code = script, pathResolver = resolver);
      val answ = task.call;
      if (answ.isDefined) throw answ.get;
    }
  }
}