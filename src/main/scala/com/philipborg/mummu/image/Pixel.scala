package com.philipborg.mummu.image

import java.math.RoundingMode

import com.google.common.math.IntMath

class IllegalColour(bpc: Byte, actual:Int) extends RuntimeException("Colour values for BPC " + bpc + " most be between 0 and " + (IntMath.pow(2, bpc) - 1) + " inclusive not " + actual +".");

/**
 * TODO Possibly compress it to store it using a single long instead of 5 ints.
 * 
 * A single pixel storing colour information. All pixels store Alpha, Gray, Red, Green, Blue and the BPC. All channels do however not need to be fed to the constructor.
 * Unused channels values will be generated.
 * @param bpc The number of bits per colour channel
 * @param gray The gray value
 * @param red The red value
 * @param green The green value
 * @param blue The blue value
 */
class Pixel(val bpc: Byte, val gray: Int, val alpha: Int, val red: Int, val green: Int, val blue: Int) {

  //Grayscale
  def this(bpc: Byte, gray: Int) = this(bpc, gray, IntMath.pow(2, bpc) - 1, gray, gray, gray);

  //Grayscale and alpha
  def this(bpc: Byte, gray: Int, alpha: Int) = this(bpc, gray, alpha, gray, gray, gray);

  //Truecolour with Alpha
  def this(bpc: Byte, alpha: Int, red: Int, green: Int, blue: Int, roundingMode: RoundingMode) = this(bpc, IntMath.divide(red + green + blue, 3, roundingMode), alpha, red, green, blue);
  def this(bpc: Byte, alpha: Int, red: Int, green: Int, blue: Int) = this(bpc, alpha, red, green, blue, RoundingMode.HALF_UP);

  //Truecolour
  def this(bpc: Byte, red: Int, green: Int, blue: Int) = this(bpc, IntMath.pow(2, bpc) - 1, red, green, blue);
  def this(bpc: Byte, red: Int, green: Int, blue: Int, roundingMode: RoundingMode) = this(bpc, IntMath.pow(2, bpc) - 1, red, green, blue, roundingMode);

  //Argument checking
  {
    if (bpc != 1 && bpc != 2 && bpc != 4 && bpc != 8 && bpc != 16) throw new IllegalArgumentException("BPC most be 1,2,4,8 or 16.");
    val maxValue = IntMath.pow(2, bpc) - 1;
    val minValue = 0;
    if (gray < 0 || gray > maxValue) throw new IllegalColour(bpc, gray);
    if (alpha < 0 || alpha > maxValue) throw new IllegalColour(bpc, alpha);
    if (red < 0 || red > maxValue) throw new IllegalColour(bpc, red);
    if (green < 0 || green > maxValue) throw new IllegalColour(bpc, green);
    if (blue < 0 || blue > maxValue) throw new IllegalColour(bpc, blue);
  }
}