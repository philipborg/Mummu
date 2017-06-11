package com.philipborg.mummu

object Main {
  def main(args:Array[String]):Unit = {
    if(args.length == 0) new SimpleGUI().main(args);
    else if(args.length > 0) new CLI().run;
    else throw new IllegalArgumentException;
  }
}