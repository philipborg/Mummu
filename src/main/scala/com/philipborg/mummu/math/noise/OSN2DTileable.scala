package com.philipborg.mummu.math.noise

import scala.util.Random

import com.kurtspencer.math.noise.OpenSimplexNoise

class OSN2DTileable(seed: Option[Long] = None, width: Long, height: Long) extends Noise2D {
  protected val osn = if (seed.isDefined) new OpenSimplexNoise(seed.get) else new OpenSimplexNoise(Random.nextLong);
  protected val Pi2 = math.Pi * 2;
  def eval(x: Double, y: Double): Double = {
    val s = (x / width) * Pi2;
    val t = (y / height) * Pi2;

    val nx = math.cos(s) * width / Pi2;
    val ny = math.cos(t) * height / Pi2;
    val nz = math.sin(s) * width / Pi2;
    val nw = math.sin(t) * height / Pi2;

    return osn.eval(nx, ny, nz, nw);
  }
}