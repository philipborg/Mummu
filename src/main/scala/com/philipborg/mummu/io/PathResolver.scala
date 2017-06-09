package com.philipborg.mummu.io

import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Path

trait PathResolver {
  def allowedUserPath(path: String): Boolean;
  def resolvePathToOutputstream(path: String, append: Boolean = false): OutputStream;
  def resolvePathToInputstream(path: String): InputStream;
}