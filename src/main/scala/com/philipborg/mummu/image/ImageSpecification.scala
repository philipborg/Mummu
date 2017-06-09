package com.philipborg.mummu.image

import com.google.common.math.IntMath

trait ImageSpecification {
  if (bpc != 1 && bpc != 2 && bpc != 4 && bpc != 8 && bpc != 16) throw new IllegalArgumentException("BPC most be 1, 2, 4, 8 or 16.");
  val width: Int;
  val height: Int;
  val bpc: Byte;
  val grayscale: Boolean;
  val alpha: Boolean;
  val maxValue: Int = IntMath.pow(2, bpc) - 1;
  val channels: Int = (if (grayscale) 1 else 3) + (if (alpha) 1 else 0);
  val bpp: Int = channels * bpc
}