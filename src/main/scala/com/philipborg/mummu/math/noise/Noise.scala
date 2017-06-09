package com.philipborg.mummu.math.noise

class NoiseAPI {
  def osn2D(seed: Long) = new OSN2D(Some(seed));
  def osn2D = new OSN2D;

  def osn3D(seed: Long) = new OSN3D(Some(seed));
  def osn3D = new OSN3D(None);

  def osn4D(seed: Long) = new OSN4D(Some(seed));
  def osn4D = new OSN4D(None);

  def osn2DTileable(seed: Long, width: Long, height: Long) = new OSN2DTileable(Some(seed), width, height);
  def osn2DTileable(width: Long, height: Long) = new OSN2DTileable(None, width, height);
}