package com.philipborg.mummu.io

import java.io.InputStream
import java.io.OutputStream

import org.apache.commons.vfs2.FileObject
import org.apache.commons.vfs2.VFS
import java.nio.file.Path

class WebDavResolver(username: String, password: String, hostname: String, port: Int) extends PathResolver {

  protected val fsMan = VFS.getManager;
  private val webDav = "webdav://" + username + ":" + password + "@" + hostname + ":" + port + "/";

  def allowedUserPath(path: String): Boolean = {
    if (path.size == 0) return false;
    return path.forall { c => Character.isLetterOrDigit(c) || c == '.' } && path.head.isLetterOrDigit && path.last.isLetterOrDigit;
  }

  protected def resolveFileObject(path: String): FileObject = {
    if (!allowedUserPath(path)) throw new IllegalArgumentException("Only alphanumerical and . is allowed for filepaths, it most also start and end with a alphanumerical character.");
    val fobj = fsMan.resolveFile(webDav + path);
    return fobj;
  }

  def resolvePathToOutputstream(path: String, append: Boolean = false): OutputStream = {
    val fobj = resolveFileObject(path);
    return fobj.getContent.getOutputStream(append);
  }

  def resolvePathToInputstream(path: String): InputStream = {
    val fobj = resolveFileObject(path);
    return fobj.getContent.getInputStream;
  }
}