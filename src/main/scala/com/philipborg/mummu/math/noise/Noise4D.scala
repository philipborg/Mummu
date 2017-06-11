package com.philipborg.mummu.math.noise

trait Noise4D {
  def eval(x: Double, y: Double, z: Double, w: Double): Double;
}