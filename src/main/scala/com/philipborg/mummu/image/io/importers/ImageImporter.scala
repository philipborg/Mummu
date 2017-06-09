package com.philipborg.mummu.image.io.importers

import java.io.InputStream
import com.philipborg.mummu.image.Image
import com.philipborg.mummu.image.ImageSpecification

trait ImageImporter {
  def apply(inputStream:InputStream, imageSpawner:(ImageSpecification) => Image):Image;
}