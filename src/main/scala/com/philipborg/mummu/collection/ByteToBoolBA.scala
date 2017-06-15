package com.philipborg.mummu.collection

import java.math.RoundingMode

import com.google.common.math.LongMath

class ByteToBoolBA(val size: Long, byteArray: (Long) => BigArray[Byte]) extends BigArray[Boolean] {
  protected val underlying: BigArray[Byte] = byteArray(LongMath.divide(size, 8, RoundingMode.UP));
  def close(): Unit = underlying.close;

  def apply(index: Long): Boolean = {
    val byte = underlying(LongMath.divide(index, 8, RoundingMode.DOWN));
    return ((byte >>> (index % 8)) & 1) != 0;
  }

  def update(index: Long, value: Boolean): Unit = {
    val uIndex = LongMath.divide(index, 8, RoundingMode.DOWN);
    val byte = underlying(uIndex);
    val changedByte: Byte = if (value)
      (byte | (1 << (index % 8))).toByte;
    else
      (byte & (~(1 << (index % 8)))).toByte;
    underlying(uIndex) = changedByte;
  }
  override def fill(eval: Long => Boolean): Unit = (0l until size).foreach(i => update(i, eval(i)));
  override def fill(eval: => Boolean): Unit = (0l until size).foreach(i => update(i, eval));
}