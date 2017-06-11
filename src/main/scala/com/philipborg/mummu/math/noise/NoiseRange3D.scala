package com.philipborg.mummu.math.noise

class NoiseRange3D(underlying: Noise3D, protected val min: Double, protected val max: Double) extends Noise3D with NoiseRange {
  def eval(x: Double, y: Double, z: Double): Double = map(underlying.eval(x, y, z));
}