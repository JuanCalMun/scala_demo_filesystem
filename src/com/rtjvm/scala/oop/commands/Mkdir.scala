package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.files.Directory
import com.rtjvm.scala.oop.filesystem.State

class Mkdir(newFileName: String) extends Command {

  def printAllreadyExist(fileName: String): String = s"Entry $fileName already exists!"

  def printCreatedDir(fileName: String): String = s"$fileName created successfully"

  def printIllegalCreate(fileName: String): String = s"$fileName must not contain separators"

  def checkIllegal(newFileName: String): Boolean =
    newFileName.contains(Directory.SEPARATOR) || newFileName.contains(".")

  def execMkdir(): State = ???

  override def apply(state: State): State = {
    val workingDirectory = state.workingDirectory
    if (workingDirectory.hasEntry(newFileName))
      state.setMessage(printAllreadyExist(newFileName))
    else if (checkIllegal(newFileName))
      state.setMessage(printIllegalCreate(newFileName))
    else {
      execMkdir()
    }

  }
}





