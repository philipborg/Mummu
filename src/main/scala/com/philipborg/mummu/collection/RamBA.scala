package com.philipborg.mummu.collection

import java.math.RoundingMode

import scala.collection.mutable.ArrayBuffer
import scala.reflect.ClassTag

import com.google.common.math.LongMath

class RamBA[T: ClassTag](val size: Long) extends BigArray[T] {

  if (size > (4611686014132420609l)) throw new IllegalArgumentException("RamBA does not support arrays bigger than (2^31 - 1)^2 elements.");
  if (size < 0) throw new IllegalArgumentException("Size can't be less than 0.");
  protected val elements: Array[Array[T]] = {
    val arrays = new ArrayBuffer[Array[T]]();
    var filled: Long = 0l;
    while (filled < size) {
      val arraySize = Math.min(Int.MaxValue, size - filled).toInt;
      arrays += new Array[T](arraySize);
      filled += arraySize;
    }
    arrays.toArray;
  }

  def close: Unit = return ;

  def apply(index: Long): T = {
    val outerIndex = LongMath.divide(index, Int.MaxValue, RoundingMode.DOWN).toInt;
    val innerIndex = (index % Int.MaxValue).toInt;
    return elements(outerIndex)(innerIndex);
  }

  def update(index: Long, value: T): Unit = {
    val outerIndex = LongMath.divide(index, Int.MaxValue, RoundingMode.DOWN).toInt;
    val innerIndex = (index % Int.MaxValue).toInt;
    elements(outerIndex)(innerIndex) = value;
  }
}