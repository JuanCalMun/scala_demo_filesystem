package com.rtjvm.scala.oop.files

class Directory(override val parentPath: String, override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentPath, name) {
  def hasEntry(name: String): Boolean = findEntry(name) != null

  def findEntry(entryName: String): DirEntry = {
    contents.find(e => e.name.eq(entryName)).orNull
  }

  def addEntry(newEntry: DirEntry): Directory =
    new Directory(parentPath, name, newEntry :: contents)

  def getAllFoldersInPath: List[String] =
    path.substring(1).split(Directory.SEPARATOR).toList

  def findDescendant(path: List[String]): Directory = ???

  def replaceEntry(entryName: String, newEntry: DirEntry): Directory = ???

  override def asDirectory: Directory = this
}

object Directory {
  val ROOT_PATH = "/"
  val SEPARATOR = "/"

  def ROOT: Directory = Directory.empty("", "")

  def empty(parentPath: String, name: String): Directory =
    new Directory(parentPath, name, List())
}