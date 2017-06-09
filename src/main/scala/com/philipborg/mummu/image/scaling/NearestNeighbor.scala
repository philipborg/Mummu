package com.philipborg.mummu.image.scaling

import com.philipborg.mummu.image.Image

/**
 * A nearestneighbor image rescaling method.
 *
 */
class NearestNeighbor(source: Image, width: Int, height: Int) extends Scaler {

  if (width < 0 || height < 0) throw new IllegalArgumentException("Image dimensions can't be negative.");

  def call: Image = {
    val scaledImg = source.newSiblingImage(width, height);
    val xScale = source.width.toDouble / width.toDouble;
    val yScale = source.height.toDouble / height.toDouble;
    for (x <- 0 until width par) {
      for (y <- 0 until height) {
        //TODO This feels really sub-optimal
        scaledImg.setPixel(x, y, source.getPixel(math.min(math.round(x * xScale).toInt, source.width - 1), math.min(math.round(y * yScale).toInt, source.height - 1)));
      }
    }
    return scaledImg;
  }
}