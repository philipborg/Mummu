package com.philipborg.mummu.math.noise

import scala.util.Random

import com.kurtspencer.math.noise.OpenSimplexNoise

class OSN3D(seed: Option[Long] = None) extends Noise3D {
  private val osn = if (seed.isDefined) new OpenSimplexNoise(seed.get) else new OpenSimplexNoise(Random.nextLong);
  def eval(x: Double, y: Double, z: Double): Double = osn.eval(x, y, z);
}