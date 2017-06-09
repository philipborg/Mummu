package com.philipborg.mummu.collection

trait BigArray[T] extends AutoCloseable {
  val size: Long;
  def apply(index: Long): T;
  def update(index: Long, value: T): Unit;
  def fill(eval: Long => T): Unit = (0l until size par).foreach(i => update(i, eval(i)));
  def fill(eval: => T): Unit = (0l until size par).foreach(i => update(i, eval));

}