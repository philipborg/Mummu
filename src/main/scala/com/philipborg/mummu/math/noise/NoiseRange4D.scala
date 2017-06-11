package com.philipborg.mummu.math.noise

class NoiseRange4D(underlying: Noise4D, protected val min: Double, protected val max: Double) extends Noise4D with NoiseRange {
  def eval(x: Double, y: Double, z: Double, w: Double): Double = map(underlying.eval(x, y, z, w));
}