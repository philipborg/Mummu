package com.philipborg.mummu

import java.io.OutputStreamWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

import com.philipborg.mummu.io.FileResolver

import scalafx.Includes.handle
import scalafx.application.JFXApp
import scalafx.beans.binding.Bindings
import scalafx.beans.property.ObjectProperty
import scalafx.beans.property.StringProperty
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.control.ButtonBar
import scalafx.scene.control.TextArea
import scalafx.scene.control.TextField
import scalafx.scene.layout.BorderPane
import scalafx.scene.paint.Color.Black
import scalafx.stage.DirectoryChooser
import scalafx.stage.FileChooser

/**
 * This should really be called Shitty GUI. As poorly designed as programmed, writing it was however very fast.
 */
class SimpleGUI extends JFXApp {

  val charset = ObjectProperty(StandardCharsets.UTF_8);

  val loadButton = new Button("LOAD") {
    onMouseClicked = handle {
      val fc = new FileChooser {
        title = "Open Mummu File";
        extensionFilters.addAll(
          new FileChooser.ExtensionFilter("All extension", "*"),
          new FileChooser.ExtensionFilter("JavaScript", "*.js"),
          new FileChooser.ExtensionFilter("EcmaScript", "*.es"),
          new FileChooser.ExtensionFilter("Mummu Script", "*.mums"));
        initialDirectory = new java.io.File(workingDirectory.value);
      }
      val file = fc.showOpenDialog(stage);
      if (file != null) loadFile(file.toPath());
      else println("No file selected.");
    }
  }

  def execute = {
    val writer = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
    val req = new Request(code = code.value, pathResolver = new FileResolver(workingDirectory.value), writer = Some(writer));
    val resp = req.call;
    if (resp.isDefined) resp.get.printStackTrace();
  }

  val selectWorkDirectoryButton = new Button("...") {
    onMouseClicked = handle {
      new DirectoryChooser {
        title = "Select working directory";
        val file = showDialog(stage);
        if (file != null) workingDirectory.value = file.toString;
        else println("No directory selected.");
      }
    }
  }

  def save {
    if (workFile.value.isEmpty) saveAs;
    else {
      val path = Paths.get(workFile.value.get);
      val data = code.value.getBytes(charset.value);
      Files.write(path, data, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }
  }

  def saveAs = {
    val fc = new FileChooser {
      title = "Save file as...";
      extensionFilters.addAll(
        new FileChooser.ExtensionFilter("All extension", "*"),
        new FileChooser.ExtensionFilter("JavaScript", "*.js"),
        new FileChooser.ExtensionFilter("EcmaScript", "*.es"),
        new FileChooser.ExtensionFilter("Mummu Script", "*.mums"));
      initialDirectory = new java.io.File(workingDirectory.value);
    }
    val file = fc.showSaveDialog(stage);
    if (file != null) {
      workFile.value = Some(file.toString);
      save;
    } else println("No save location selected.");
  }

  val workingDirectory = StringProperty(System.getProperty("user.home") + java.io.File.separator + "MUMMU");
  (new java.io.File(workingDirectory.value)).mkdirs();
  val code = StringProperty("");
  val workFile: ObjectProperty[Option[String]] = ObjectProperty(None);

  def loadFile(path: Path) {
    code.value = new String(Files.readAllBytes(path), charset.value);
    workFile.value = Some(path.toString);
  }

  def newFile() {
    code.value = "";
    workFile.value = None;
  }

  stage = new JFXApp.PrimaryStage {
    title <== Bindings.createStringBinding(() => "MUMMU SIMPLE-GUI " + Option(workFile.value).getOrElse(""), workFile);
    width = 800;
    height = 600;
    resizable = true;
    scene = new Scene {
      fill = Black;
      root = new BorderPane {
        top = new ButtonBar {
          buttons = Seq(
            new Button("NEW") { onMouseClicked = handle { newFile } },
            loadButton,
            new Button("SAVE") { onMouseClicked = handle { save } },
            new Button("SAVE AS") { onMouseClicked = handle { saveAs } },
            new Button("EXECUTE") { onMouseClicked = handle { execute } },
            new TextField {
              text <==> workingDirectory;
              prefColumnCount <== text.length;
            },
            selectWorkDirectoryButton)
        }

        center = new TextArea {
          text <==> code;
          editable = true;
        }

      }
    }
  }

}