package com.philipborg.mummu.math.noise

import scala.util.Random

import com.kurtspencer.math.noise.OpenSimplexNoise

class OSN4D(seed: Option[Long] = None) extends Noise4D {
  private val osn = if (seed.isDefined) new OpenSimplexNoise(seed.get) else new OpenSimplexNoise(Random.nextLong);
  def eval(x: Double, y: Double, z: Double, w: Double): Double = osn.eval(x, y, z, w);
}