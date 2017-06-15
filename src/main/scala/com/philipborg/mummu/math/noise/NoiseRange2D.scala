package com.philipborg.mummu.math.noise

class NoiseRange2D(underlying: Noise2D, protected val min: Double, protected val max: Double) extends Noise2D with NoiseRange {
  def eval(x: Double, y: Double): Double = map(underlying.eval(x, y));
}