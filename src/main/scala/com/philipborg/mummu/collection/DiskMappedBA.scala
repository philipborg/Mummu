package com.philipborg.mummu.collection

import java.nio.channels.SeekableByteChannel
import scala.collection.mutable.ArrayBuffer
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Path
import java.nio.file.OpenOption
import java.nio.file.StandardOpenOption
import java.nio.MappedByteBuffer
import com.google.common.math.LongMath
import java.math.RoundingMode
import java.nio.channels.FileChannel.MapMode
import com.google.common.math.IntMath

class DiskMappedBA(path: Path, val size: Long) extends BigArray[Byte] {

  if (size > (4611686014132420609l)) throw new IllegalArgumentException("DiskMappedBA does not support arrays bigger than (2^31 - 1)^2 elements.");
  if (size < 0) throw new IllegalArgumentException("Size can't be less than 0.");

  protected val seeker: FileChannel = {
    val options = Seq(
      StandardOpenOption.DELETE_ON_CLOSE,
      StandardOpenOption.WRITE,
      StandardOpenOption.READ,
      StandardOpenOption.SPARSE,
      StandardOpenOption.CREATE,
      StandardOpenOption.TRUNCATE_EXISTING);
    FileChannel.open(path, options: _*);
  }

  protected val mappings: Seq[MappedByteBuffer] = {
    val arrays = ArrayBuffer.empty[MappedByteBuffer];
    var filled: Long = 0l;
    while (filled < size) {
      val arraySize = Math.min(Int.MaxValue, size - filled).toInt;
      arrays += seeker.map(MapMode.READ_WRITE, filled, arraySize);
      filled += arraySize;
    }
    arrays.toSeq;
  }

  def apply(index: Long): Byte = {
    return mappings(LongMath.divide(index, Int.MaxValue, RoundingMode.DOWN).toInt).get((index % Int.MaxValue).toInt);
  }
  
  def update(index: Long, value: Byte): Unit = {
    mappings(LongMath.divide(index, Int.MaxValue, RoundingMode.DOWN).toInt).put((index % Int.MaxValue).toInt, value);
  }

  def close: Unit = {
    seeker.close;
  }
}