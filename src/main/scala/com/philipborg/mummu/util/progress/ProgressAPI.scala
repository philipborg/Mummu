package com.philipborg.mummu.util.progress

import java.io.PrintStream

class ProgressAPI(printStream: PrintStream) {
  def default(completed: Long) = new PrintPartsProgress(printStream, completed);
  def default(completed: Long, taskName: String) = new PrintPartsProgress(printStream, completed, Some(taskName));
  def default(completed: Long, nanoSecondsInterval: Long) = new PrintPartsProgress(printStream, completed, None, nanoSecondsInterval);
  def default(completed: Long, taskName: String, nanoSecondsInterval: Long) = new PrintPartsProgress(printStream, completed, Some(taskName), nanoSecondsInterval);
}