package com.rtjvm.scala.oop.files

import java.nio.file.FileSystemException

class File(override val parentPath: String,
           override val name: String,
           val content: String) extends DirEntry(parentPath, name) {
  override def asDirectory: Directory =
    throw new FileSystemException("A file cannot be converted to a directory")

  override def asFile: File =
    this

  override def getType: String = "File"

  override def isFile: Boolean = true
}

object File {
  def empty(parentPath: String, name: String) =
    new File(parentPath, name, "")
}
