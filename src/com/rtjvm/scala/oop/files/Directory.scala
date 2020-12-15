package com.rtjvm.scala.oop.files

import java.nio.file.FileSystemException

import com.rtjvm.scala.oop.files.Directory.SEPARATOR

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {
  def hasEntry(name: String): Boolean = contents.map(_.name).contains(name)

  def findEntry(entryName: String): DirEntry = {
    contents.find(_.name == entryName).orNull
  }

  def addEntry(newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents :+ newEntry)

  def getAllFoldersInPath: List[String] =
    path.substring(1).split(Directory.SEPARATOR).toList.filter(!_.isEmpty)

  def findDescendant(path: List[String]): Directory =
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory.findDescendant(path.tail)

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory =
    new Directory(parentPath, name, contents.filter(!_.name.eq(entryName)) :+ newEntry)

  override def asDirectory: Directory = this

  override def asFile: File =
    throw new FileSystemException("A directory cannot be converted to a file")

  override def getType: String = "Directory"

  def isRoot: Boolean = parentPath.isEmpty

  override def isDirectory: Boolean = true

  override def toString: String =
    if (name == "") SEPARATOR
    else if (parentPath == SEPARATOR) SEPARATOR + name
    else parentPath + SEPARATOR + name

}

object Directory {
  val ROOT_PATH = "/"
  val SEPARATOR = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath, name, List())
}
