package com.rtjvm.scala.oop.filesystem

import com.rtjvm.scala.oop.files.Directory

class State(val root: Directory, val workingDirectory: Directory, val output: String) {


  def show: Unit = {
    if (!output.isBlank) println(output)
    print(State.SHELL_TOKEN)
  }

  def setMessage(message: String): State =
    State(root, workingDirectory, message)

  def setWorkingDirectory(workingDirectory: Directory): State =
    State(root, workingDirectory, "")

}

object State {
  val SHELL_TOKEN = "> "

  def apply(root: Directory, workingDirectory: Directory, output: String = ""): State =
    new State(root, workingDirectory, output)
}
