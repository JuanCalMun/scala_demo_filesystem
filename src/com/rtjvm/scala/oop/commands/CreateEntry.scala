package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.files.{DirEntry, Directory}
import com.rtjvm.scala.oop.filesystem.State

abstract class CreateEntry(newEntryName: String) extends Command {

  override def apply(state: State): State = {
    val workingDirectory = state.workingDirectory
    if (workingDirectory.hasEntry(newEntryName))
      state.setMessage(printAllreadyExist(newEntryName))
    else if (checkIllegal(newEntryName))
      state.setMessage(printIllegalCreate(newEntryName))
    else {
      exec(state, newEntryName)
    }
  }

  def checkIllegal(newFileName: String): Boolean =
    newFileName.contains(Directory.SEPARATOR) || newFileName.contains(".")

  def exec(state: State, name: String): State = {
    val workingDirectory = state.workingDirectory

    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }
    }

    val allDirsInPath = workingDirectory.getAllFoldersInPath
    val newEntry: DirEntry = doCreateEntry(workingDirectory.path, name)
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)
    val newWorkingDirectory = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWorkingDirectory)
  }

  def printAllreadyExist(fileName: String): String = s"Entry $fileName already exists!"

  def printIllegalCreate(fileName: String): String = s"$fileName must not contain separators"

  def doCreateEntry(path: String, entryName: String): DirEntry

  def printCreatedDir(fileName: String): String = s"$fileName created successfully"

}
