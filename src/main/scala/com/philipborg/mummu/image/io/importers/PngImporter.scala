package com.philipborg.mummu.image.io.importers

import java.io.InputStream

import com.philipborg.mummu.image.Image
import com.philipborg.mummu.image.ImageSpecification
import com.philipborg.mummu.image.Pixel

import ar.com.hjg.pngj.ImageLineInt
import ar.com.hjg.pngj.PngReader

object PngImporter extends ImageImporter {
  def apply(inputStream: InputStream, imageSpawner: (ImageSpecification) => Image): Image = {
    val pngr = new PngReader(inputStream, true);
    val imgInfo = pngr.imgInfo;

    val imageSpec = new ImageSpecification {
      val width: Int = imgInfo.cols;
      val height: Int = imgInfo.rows;
      val bpc: Byte = imgInfo.bitDepth.toByte;
      val grayscale: Boolean = imgInfo.greyscale;
      val alpha: Boolean = imgInfo.alpha;
    }

    val image: Image = imageSpawner(imageSpec);

    val bpc = imageSpec.bpc;
    if (imgInfo.greyscale && imgInfo.alpha) {
      //Grayscale and alpha
      for (y <- 0 until imgInfo.rows) {
        val imageLine = pngr.readRow.asInstanceOf[ImageLineInt].getScanline;
        for (x <- 0 until imgInfo.cols) {
          val offset = x * 2;
          image.setPixel(x, y, new Pixel(bpc, imageLine(offset), imageLine(offset + 1)));
        }
      }
    } else if (imgInfo.greyscale) {
      //Grayscale
      for (y <- 0 until imgInfo.rows) {
        val imageLine = pngr.readRow.asInstanceOf[ImageLineInt].getScanline;
        for (x <- 0 until imgInfo.cols) {
          image.setPixel(x, y, new Pixel(bpc, imageLine(x)));
        }
      }
    } else if (imgInfo.alpha) {
      //Truecolour and alpha
      for (y <- 0 until imgInfo.rows) {
        val imageLine = pngr.readRow.asInstanceOf[ImageLineInt].getScanline;
        for (x <- 0 until imgInfo.cols) {
          val offset = x * 4;
          image.setPixel(x, y, new Pixel(bpc = bpc, red = imageLine(offset), green = imageLine(offset + 1), blue = imageLine(offset + 2), alpha = imageLine(offset + 3)));
        }
      }
    } else {
      //Truecolour
      for (y <- 0 until imgInfo.rows) {
        val imageLine = pngr.readRow.asInstanceOf[ImageLineInt].getScanline;
        for (x <- 0 until imgInfo.cols) {
          val offset = x * 3;
          image.setPixel(x, y, new Pixel(bpc = bpc, red = imageLine(offset), green = imageLine(offset + 1), blue = imageLine(offset + 2)));
        }
      }
    }

    return image;
  }
}