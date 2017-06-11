package com.philipborg.mummu.io.text

import java.nio.charset.Charset

trait TextAPI {
  def loadText(path: String, charset: Charset): String;
  def saveText(out: Any, path: String, charset: Charset, append: Boolean = false);
  def ls: String;
}