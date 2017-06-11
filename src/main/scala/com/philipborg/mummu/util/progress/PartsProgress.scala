package com.philipborg.mummu.util.progress

import java.util.concurrent.atomic.AtomicLong

trait PartsProgress extends Progress {
  protected val progress = new AtomicLong();
  val complete: Long;
  def addPart(amount: Long): Unit = progress.addAndGet(amount);
  def addPart: Unit = progress.incrementAndGet;
  def setCompletion(percentage: Double): Unit = progress.set((complete * percentage).toLong);
  def setParts(amount: Long): Unit = progress.set(amount);
}