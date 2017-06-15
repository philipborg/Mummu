package com.philipborg.mummu.io

import java.io.InputStream
import java.io.OutputStream

import org.apache.commons.vfs2.FileObject
import org.apache.commons.vfs2.VFS

class FileResolver(directory: String) extends PathResolver {

  protected val fsMan = VFS.getManager;
  protected val legalSymbols = Seq[Char]('.', '/', '-', '_');

  def allowedUserPath(path: String): Boolean = {
    if (path.size == 0) return false;
    return path.forall { c => Character.isLetterOrDigit(c) || legalSymbols.contains(c) } && path.head.isLetterOrDigit && path.last.isLetterOrDigit;
  }

  protected def resolveFileObject(path: String): FileObject = {
    if (!allowedUserPath(path)) throw new IllegalArgumentException("Invalid filename. Received-" + path);
    return fsMan.resolveFile(directory + "/" + path)
  }

  def resolvePathToInputstream(path: String): InputStream = {
    resolveFileObject(path).getContent.getInputStream;
  }
  def resolvePathToOutputstream(path: String, append: Boolean = false): OutputStream = {
    resolveFileObject(path).getContent.getOutputStream(append);
  }

}