package com.philipborg.mummu.image.io.exporters

import java.io.OutputStream

import com.philipborg.mummu.image.Image

import ar.com.hjg.pngj.ImageInfo
import ar.com.hjg.pngj.ImageLineInt
import ar.com.hjg.pngj.PngWriter
import ar.com.hjg.pngj.chunks.PngChunkTextVar

object PngExporter extends ImageExporter {

  def apply(output: OutputStream, image: Image, params: String): Unit = {
    val imgInfo = new ImageInfo(image.width, image.height, image.bpc, image.alpha, image.grayscale, false);
    if (imgInfo.cols.toLong * imgInfo.channels.toLong > Int.MaxValue.toLong) throw new IllegalArgumentException("The PNG writer only supports images where width * channels is less than (2^31)-1.");
    val pngWriter = new PngWriter(output, imgInfo);
    pngWriter.setCompLevel(if (params.isEmpty) 9 else params.toInt);
    pngWriter.getMetadata.setTimeNow;
    //TODO Allow params metadata
    pngWriter.getMetadata.setText(PngChunkTextVar.KEY_Software, "Mummu by philipborg");
    pngWriter.getMetadata.setText("SoftwareLink", "https://github.com/philipborg/Mummu");
    try {
      for (y <- 0 until image.height) {
        val imgLineInt = new ImageLineInt(imgInfo);
        (0 until image.width par).foreach { x =>
          val pixel = image.getPixel(x, y);
          if (imgInfo.greyscale && imgInfo.alpha) {
            //Grayscale and alpha
            val ix = x * 2;
            imgLineInt.getScanline(ix) = pixel.gray;
            imgLineInt.getScanline(ix + 1) = pixel.alpha;
          } else if (imgInfo.greyscale) {
            //Grayscale
            imgLineInt.getScanline(x) = pixel.gray;
          } else if (imgInfo.alpha) {
            //Truecolour and alpha
            val ix = x * 4;
            imgLineInt.getScanline(ix) = pixel.red;
            imgLineInt.getScanline(ix + 1) = pixel.green;
            imgLineInt.getScanline(ix + 2) = pixel.blue;
            imgLineInt.getScanline(ix + 3) = pixel.alpha;
          } else {
            //Truecolour
            val ix = x * 3;
            imgLineInt.getScanline(ix) = pixel.red;
            imgLineInt.getScanline(ix + 1) = pixel.green;
            imgLineInt.getScanline(ix + 2) = pixel.blue;
          }
        }
        pngWriter.writeRow(imgLineInt);
      }
    } finally {
      pngWriter.end;
    }
  }
}