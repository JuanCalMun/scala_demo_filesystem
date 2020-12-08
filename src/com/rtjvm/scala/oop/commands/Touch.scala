package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.files.File

class Touch(fileName: String) extends CreateEntry(fileName) {
  override def doCreateEntry(path: String, name: String): File =
    File.empty(path, name)
}
