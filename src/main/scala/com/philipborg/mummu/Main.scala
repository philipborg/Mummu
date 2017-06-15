package com.philipborg.mummu

import java.awt.GraphicsEnvironment

object Main {
  def main(args:Array[String]):Unit = {
    if(args.length == 0 && !GraphicsEnvironment.isHeadless()) new SimpleGUI().main(args);
    else new CLI().run(args);
  }
}