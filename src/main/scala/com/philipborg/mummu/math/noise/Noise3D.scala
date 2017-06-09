package com.philipborg.mummu.math.noise

trait Noise3D {
  def eval(x:Double, y:Double, z:Double):Double;
}