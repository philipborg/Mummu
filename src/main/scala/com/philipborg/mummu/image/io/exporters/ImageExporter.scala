package com.philipborg.mummu.image.io.exporters

import java.io.OutputStream

import com.philipborg.mummu.image.Image

trait ImageExporter {
  def apply(output: OutputStream, image: Image, params: String): Unit;
}