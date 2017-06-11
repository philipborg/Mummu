package com.philipborg.mummu.math.noise

import scala.util.Random

import com.kurtspencer.math.noise.OpenSimplexNoise

class OSN2D(seed: Option[Long] = None) extends Noise2D {
  protected val osn = if (seed.isDefined) new OpenSimplexNoise(seed.get) else new OpenSimplexNoise(Random.nextLong);
  def eval(x: Double, y: Double): Double = osn.eval(x, y);
}