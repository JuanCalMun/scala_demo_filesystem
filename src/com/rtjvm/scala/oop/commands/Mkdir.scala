package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.files.{DirEntry, Directory}
import com.rtjvm.scala.oop.filesystem.State

class Mkdir(newFileName: String) extends Command {

  def printCreatedDir(fileName: String): String = s"$fileName created successfully"

  override def apply(state: State): State = {
    val workingDirectory = state.workingDirectory
    if (workingDirectory.hasEntry(newFileName))
      state.setMessage(printAllreadyExist(newFileName))
    else if (checkIllegal(newFileName))
      state.setMessage(printIllegalCreate(newFileName))
    else {
      execMkdir(state, newFileName)
    }

  }

  def printAllreadyExist(fileName: String): String = s"Entry $fileName already exists!"

  def printIllegalCreate(fileName: String): String = s"$fileName must not contain separators"

  def checkIllegal(newFileName: String): Boolean =
    newFileName.contains(Directory.SEPARATOR) || newFileName.contains(".")

  def execMkdir(state: State, name: String): State = {
    val workingDirectory = state.workingDirectory

    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    /*
    *   1.  Get all the directories in the full path
    */
    val allDirsInPath = workingDirectory.getAllFoldersInPath

    /*
    *   2.  Create new directory entry in the working directory
    */
    val newDir = Directory.empty(workingDirectory.path, name)

    /*
    *   3.  Update the whole directory structure starting from the root (The directory structure is immutable)
    */
    val newRoot = updateStructure(state.root, allDirsInPath, newDir)

    /*
    *   4.  Find the new working directory instance given working directory full path, in the new directory structure
    */
    val newWorkingDirectory = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWorkingDirectory)
  }

}
