package com.philipborg.mummu.util.progress

import java.text.DecimalFormat
import java.util.Timer
import java.util.TimerTask
import java.util.concurrent.TimeUnit
import java.io.PrintStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class PrintPartsProgress(printStream: PrintStream, val complete: Long, val taskName: Option[String] = None, val interval: Long = 2, val unit: TimeUnit = TimeUnit.SECONDS, val msDelay: Long = 0) extends PartsProgress {
  protected val formater = new DecimalFormat("#0.00");
  protected val timer: Timer = new Timer(true) {
    val timerTask = new TimerTask {
      def run = {
        printStream.println(progress + "/" + complete + " (" + formater.format(100d * (progress.doubleValue / complete.toDouble)) + "%) completed of task " + taskName.getOrElse(""));
      }
    }
    scheduleAtFixedRate(timerTask, msDelay, unit.toMillis(interval));
  }

  protected val startTime = System.currentTimeMillis();

  def close = {
    timer.cancel();
    val dateFormater = new SimpleDateFormat("HH:mm:ss.SSS");
    dateFormater.setTimeZone(TimeZone.getTimeZone("GMT"));
    printStream.println(taskName.getOrElse("Task") + " closed after " + dateFormater.format(System.currentTimeMillis - startTime))
  }

}