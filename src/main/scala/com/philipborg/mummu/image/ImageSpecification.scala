package com.philipborg.mummu.image

import com.google.common.math.IntMath

trait ImageSpecification {
  val width: Int;
  val height: Int;
  val bpc: Byte;
  val grayscale: Boolean;
  val alpha: Boolean;
  //TODO Optimise
  def maxValue: Int = IntMath.pow(2, bpc) - 1;
  def channels: Int = (if (grayscale) 1 else 3) + (if (alpha) 1 else 0);
  def bpp: Int = channels * bpc;
}