package com.philipborg.mummu.math.noise

import com.kurtspencer.math.noise.OpenSimplexNoiseTileable3D

class OSN3DTileable(seed: Option[Long] = None, width: Long, height: Long, depth: Long) extends Noise3D {

  //Parameter checking
  if (width % 6 != 0 || height % 6 != 0 || depth % 6 != 0) throw new IllegalArgumentException("OSN3DTileable only supports wrapping dimensions of a multiplier by 6. Example: 6, 12, 18 etc.");
  if (width / 6 > Int.MaxValue || height / 6 > Int.MaxValue || depth / 6 > Int.MaxValue) throw new IllegalArgumentException("OSN3DTileable only supports wrapping dimensions of at most size 12 884 901 882");

  protected val osn: OpenSimplexNoiseTileable3D = if (seed.isDefined) {
    new OpenSimplexNoiseTileable3D(seed.get, (width / 6).toInt, (height / 6).toInt, (depth / 6).toInt);
  } else {
    new OpenSimplexNoiseTileable3D((width / 6).toInt, (height / 6).toInt, (depth / 6).toInt);
  }

  def eval(x: Double, y: Double, z: Double): Double = {
    osn.eval(x, y, z);
  }
}