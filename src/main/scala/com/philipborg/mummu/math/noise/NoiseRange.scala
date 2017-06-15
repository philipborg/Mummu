package com.philipborg.mummu.math.noise

trait NoiseRange {
  protected val min: Double;
  protected val max: Double;
  private val mult = (max - min) / 2d;
  protected def map(value: Double): Double = {
    val answer = math.max(min, math.min(max, (value + 1) * mult + min));
    return answer;
  }
}