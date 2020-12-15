package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.files.{DirEntry, Directory}
import com.rtjvm.scala.oop.filesystem.State

import scala.annotation.tailrec

class Cd(dir: String) extends Command {


  override def apply(state: State): State = {

    //    Find root
    val root = state.root
    val workingDirectory = state.workingDirectory

    //    Find the absolute path of the directory I want to cd to
    val absolutePath =
      if (dir.startsWith(Directory.SEPARATOR)) dir
      else if (workingDirectory.isRoot) Directory.SEPARATOR + dir
      else workingDirectory + Directory.SEPARATOR + dir

    //    Find the directory to cd to, given the path
    val destinationDirectory = findEntry(root, absolutePath)

    //    Change the state given the new directory
    if (destinationDirectory == null || !destinationDirectory.isDirectory)
      state.setMessage("Destination path cannot be found")
    else state.setWorkingDirectory(destinationDirectory.asDirectory)

  }

  def findEntry(root: Directory, absolutePath: String): DirEntry = {
    @tailrec
    def findEntryHelper(currentDirectory: Directory, path: List[String]): DirEntry = {
      if (path.isEmpty || path.head.isEmpty) currentDirectory
      else if (path.tail.isEmpty) currentDirectory.findEntry(path.head)
      else {
        val nextDir = currentDirectory.findEntry(path.head)
        if (nextDir == null || !nextDir.isDirectory) null
        else findEntryHelper(nextDir.asDirectory, path.tail)
      }
    }

    findEntryHelper(root, absolutePath.substring(1).split(Directory.SEPARATOR).toList)
  }


}
