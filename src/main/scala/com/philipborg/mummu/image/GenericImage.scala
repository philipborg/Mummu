package com.philipborg.mummu.image

import java.util.concurrent.locks.ReentrantReadWriteLock

import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.Queue

import com.philipborg.mummu.collection.BigArray

class GenericImage(val width: Int, val height: Int, val bpc: Byte, val grayscale: Boolean, val alpha: Boolean, val bigArray: (Long) => BigArray[Boolean]) extends Image {
  if (bpc != 1 && bpc != 2 && bpc != 4 && bpc != 8 && bpc != 16) throw new IllegalArgumentException("BPC most be 1, 2, 4, 8 or 16.");
  protected val data: BigArray[Boolean] = bigArray.apply(width.toLong * height.toLong * bpp.toLong);

  protected val lock: ReentrantReadWriteLock = new ReentrantReadWriteLock(true);

  def allowed(x: Int, y: Int): Boolean = {
    return (x < width) && (y < height) && (x >= 0) && (y >= 0);
  }

  def clear(): Unit = data.fill(false);
  def close() = data.close();

  def getPixel(x: Int, y: Int): Pixel = {
    if (!allowed(x, y)) throw new IllegalArgumentException("(" + x + "," + y + ") is out of image bounds.");

    val pixData = Queue((0 until bpp).map(i => if (data(flat(x, y, i))) '1' else '0'): _*);
    val grayPC: Option[Int] = if (grayscale) {
      val gData = Seq.fill(bpc) { pixData.dequeue }.mkString;
      Some(Integer.parseUnsignedInt(gData, 2));
    } else None;

    val redPC: Option[Int] = if (!grayscale) {
      val rData = Seq.fill(bpc) { pixData.dequeue }.mkString;
      Some(Integer.parseUnsignedInt(rData, 2));
    } else None;

    val greenPC: Option[Int] = if (!grayscale) {
      val gData = Seq.fill(bpc) { pixData.dequeue }.mkString;
      Some(Integer.parseUnsignedInt(gData, 2));
    } else None;

    val bluePC: Option[Int] = if (!grayscale) {
      val bData = Seq.fill(bpc) { pixData.dequeue }.mkString;
      Some(Integer.parseUnsignedInt(bData, 2));
    } else None;

    val alphaPC: Option[Int] = if (alpha) {
      val aData = Seq.fill(bpc) { pixData.dequeue }.mkString;
      Some(Integer.parseUnsignedInt(aData, 2));
    } else None;

    if (grayscale && alpha) {
      //GS and alpha
      return new Pixel(bpc = bpc, gray = grayPC.get, alpha = alphaPC.get);
    } else if (grayscale) {
      //GS
      return new Pixel(bpc = bpc, gray = grayPC.get);
    } else if (alpha) {
      //Truecolour and alpha
      return new Pixel(bpc = bpc, alpha = alphaPC.get, red = redPC.get, green = greenPC.get, blue = bluePC.get);
    } else {
      //Truecolour
      return new Pixel(bpc = bpc, red = redPC.get, green = greenPC.get, blue = bluePC.get);
    }
  }

  def setPixel(x: Int, y: Int, pixel: Pixel): Unit = {
    if (!allowed(x, y)) throw new IllegalArgumentException("(" + x + "," + y + ") is out of image bounds.");

    val newPixel = ArrayBuffer.empty[Boolean];

    if (grayscale) {
      //Store grayscale data
      newPixel ++= intToBinary(pixel.gray);
    } else {
      //Store RGB data
      newPixel ++= intToBinary(pixel.red);
      newPixel ++= intToBinary(pixel.green);
      newPixel ++= intToBinary(pixel.blue);
    }

    if (alpha) {
      newPixel ++= intToBinary(pixel.alpha);
    }

    newPixel.zipWithIndex.foreach { b =>
      data(flat(x, y, b._2)) = b._1;
    }
  }

  protected def flat(x: Long, y: Long, c: Long): Long = {
    return x * height * bpp + y * bpp + c;
  }

  /**
   * Gets an identically configured image but with different dimensions
   */
  def newSiblingImage(width: Int, height: Int): Image = new GenericImage(width, height, this.bpc, this.grayscale, this.alpha, this.bigArray);

  protected def intToBinary(int: Int): Seq[Boolean] = {
    //Take right might be abundant
    Integer.toString(int, 2).map(_.equals('1')).reverse.padTo(bpc, false).reverse.takeRight(bpc);
  }
}