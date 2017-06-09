package com.philipborg.mummu.image

trait Image extends AutoCloseable with ImageSpecification {
  def setPixel(x: Int, y: Int, pixel: Pixel): Unit;
  def getPixel(x: Int, y: Int): Pixel;
  def clear(): Unit;
  def newSiblingImage(width: Int, height: Int): Image;
  def allowed(x:Int, y:Int):Boolean;
}