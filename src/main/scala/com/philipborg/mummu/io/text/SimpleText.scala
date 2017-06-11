package com.philipborg.mummu.io.text

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

import org.apache.commons.io.IOUtils

import com.philipborg.mummu.io.PathResolver

class SimpleText(pathResolver: PathResolver) extends TextAPI {

  def loadText(path: String, charset: Charset): String = {
    val is = pathResolver.resolvePathToInputstream(path);
    return IOUtils.toString(is, StandardCharsets.UTF_8);
  }

  def saveText(out: Any, path: String, charset: Charset, append: Boolean): Unit = {
    val os = pathResolver.resolvePathToOutputstream(path, append);
    os.write(out.toString.getBytes(charset));
    os.close;
  }

  val ls = System.lineSeparator;

}