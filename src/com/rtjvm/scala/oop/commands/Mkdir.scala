package com.rtjvm.scala.oop.commands
import com.rtjvm.scala.oop.files.Directory

class Mkdir(folderName: String) extends CreateEntry(folderName) {
  override def doCreateEntry(path: String, name: String): Directory =
    Directory.empty(path, name)

}
