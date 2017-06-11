package com.philipborg.mummu.image

import java.nio.file.Files

import org.apache.commons.io.FilenameUtils

import com.philipborg.mummu.collection.BigArray
import com.philipborg.mummu.collection.ByteToBoolBA
import com.philipborg.mummu.collection.DiskMappedBA
import com.philipborg.mummu.image.io.exporters.PngExporter
import com.philipborg.mummu.image.io.importers.PngImporter
import com.philipborg.mummu.io.PathResolver

class ImageAPI(pathResolver: PathResolver) {

  def createImage(width: Int, height: Int, bpc: Byte, grayscale: Boolean, alpha: Boolean): Image = {
    def func(size: Long): BigArray[Boolean] = {
      //val cacheBuilder = CacheBuilder.newBuilder().softValues.concurrencyLevel(Runtime.getRuntime.availableProcessors).asInstanceOf[CacheBuilder[Long, Byte]];
      return new ByteToBoolBA(size, (bytes: Long) => new DiskMappedBA(Files.createTempFile("mummu_image", ".tmp"), bytes));
    }
    return new GenericImage(width, height, bpc, grayscale, alpha, func);
  }

  protected def baseImage(imageSpec: ImageSpecification): Image = {
    return createImage(imageSpec.width, imageSpec.height, imageSpec.bpc, imageSpec.grayscale, imageSpec.alpha);
  }

  def load(path: String): Image = {
    if (!path.exists { c => c == '.' }) throw new IllegalArgumentException("File lacks extension.");
    val fileExtension = FilenameUtils.getExtension(path);
    fileExtension match {
      case "png" => return PngImporter(pathResolver.resolvePathToInputstream(path), baseImage);
      case _     => throw new IllegalArgumentException("File extension " + fileExtension + " is not a supported imageformat.");
    }
  }

  def save(image: Image, path: String): Unit = save(image, path, "");
  def save(image: Image, path: String, parameters: String): Unit = {
    if (!path.exists { c => c == '.' }) throw new IllegalArgumentException("File lacks extension.");
    val fileExtension = FilenameUtils.getExtension(path);
    fileExtension match {
      case "png" => PngExporter(pathResolver.resolvePathToOutputstream(path, false), image, parameters);
      case _     => throw new IllegalArgumentException("File extension " + fileExtension + " is not a supported imageformat.");
    }
  }

  def createPixelRGB(bpc: Byte, red: Int, green: Int, blue: Int): Pixel = {
    return new Pixel(bpc, red, green, blue);
  }

  def createPixelARGB(bpc: Byte, alpha: Int, red: Int, green: Int, blue: Int): Pixel = {
    return new Pixel(bpc = bpc, alpha = alpha, red = red, green = green, blue = blue);
  }

  def createPixelRGBA(bpc: Byte, red: Int, green: Int, blue: Int, alpha: Int): Pixel = {
    return new Pixel(bpc = bpc, alpha = alpha, red = red, green = green, blue = blue);
  }

  def createPixelG(bpc: Byte, gray: Int): Pixel = {
    return new Pixel(bpc, gray);
  }

  def createPixelGA(bpc: Byte, gray: Int, alpha: Int): Pixel = {
    return new Pixel(bpc = bpc, gray = gray, alpha = alpha);
  }

  def createPixelAG(bpc: Byte, alpha: Int, gray: Int): Pixel = {
    return new Pixel(bpc = bpc, gray = gray, alpha = alpha);
  }
}