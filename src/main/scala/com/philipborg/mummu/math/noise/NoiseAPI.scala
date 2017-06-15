package com.philipborg.mummu.math.noise

class NoiseAPI {
  def osn2D(seed: Long): Noise2D = new OSN2D(Some(seed));
  def osn2D: Noise2D = new OSN2D;

  //Ranged
  def osn2D(seed: Long, min: Double, max: Double): Noise2D = new NoiseRange2D(new OSN2D(Some(seed)), min, max);
  def osn2D(min: Double, max: Double): Noise2D = new NoiseRange2D(new OSN2D, min, max);

  def osn3D(seed: Long): Noise3D = new OSN3D(Some(seed));
  def osn3D: Noise3D = new OSN3D;

  //Ranged
  def osn3D(seed: Long, min: Double, max: Double): Noise3D = new NoiseRange3D(new OSN3D(Some(seed)), min, max);
  def osn3D(min: Double, max: Double): Noise3D = new NoiseRange3D(new OSN3D, min, max);

  def osn4D(seed: Long): Noise4D = new OSN4D(Some(seed));
  def osn4D: Noise4D = new OSN4D;

  //Ranged
  def osn4D(seed: Long, min: Double, max: Double): Noise4D = new NoiseRange4D(new OSN4D(Some(seed)), min, max);
  def osn4D(min: Double, max: Double): Noise4D = new NoiseRange4D(new OSN4D, min, max);

  def osn2DTileable(seed: Long, width: Long, height: Long): Noise2D = new OSN2DTileable(Some(seed), width, height);
  def osn2DTileable(width: Long, height: Long): Noise2D = new OSN2DTileable(None, width, height);

  //Ranged
  def osn2DTileable(seed: Long, width: Long, height: Long, min: Double, max: Double): Noise2D = new NoiseRange2D(this.osn2DTileable(seed, width, height), min, max);
  def osn2DTileable(width: Long, height: Long, min: Double, max: Double): Noise2D = new NoiseRange2D(this.osn2DTileable(width, height), min, max);

  def osn3DTileable(seed: Long, width: Long, height: Long, depth: Long): Noise3D = new OSN3DTileable(Some(seed), width, height, depth);
  def osn3DTileable(width: Long, height: Long, depth: Long): Noise3D = new OSN3DTileable(None, width, height, depth);

  //Ranged
  def osn3DTileable(seed: Long, width: Long, height: Long, depth: Long, min: Double, max: Double): Noise3D = new NoiseRange3D(this.osn3DTileable(seed, width, height, depth), min, max);
  def osn3DTileable(width: Long, height: Long, depth: Long, min: Double, max: Double): Noise3D = new NoiseRange3D(this.osn3DTileable(width, height, depth), min, max);
}