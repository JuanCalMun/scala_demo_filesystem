package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.filesystem.State

trait Command {
  def apply(state: State): State

}

object Command {
  val EXIT_INPUT_COMMAND = "exit"
  val MKDIR_INPUT_COMMAND = "mkdir"
  val LS_INPUT_COMMAND = "ls"
  val PWD_INPUT_COMMAND = "pwd"
  val TOUCH_INPUT_COMMAND = "touch"
  val CD_INPUT_COMMAND = "cd"

  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")

    if (input.isEmpty || tokens.isEmpty) emptyCommand
    else tokens(0) match {
      case EXIT_INPUT_COMMAND => new Exit
      case LS_INPUT_COMMAND => new Ls
      case PWD_INPUT_COMMAND => new Pwd
      case MKDIR_INPUT_COMMAND =>
        if (tokens.length < 2) incompleteCommand(MKDIR_INPUT_COMMAND)
        else new Mkdir(tokens(1))
      case TOUCH_INPUT_COMMAND =>
        if (tokens.length < 2) incompleteCommand(TOUCH_INPUT_COMMAND)
        else new Touch(tokens(1))
      case CD_INPUT_COMMAND =>
        if (tokens.length < 2) incompleteCommand(CD_INPUT_COMMAND)
        else new Cd(tokens(1))

      case _ => new UnknownCommand
    }
  }

  def emptyCommand: Command = (state: State) => state

  def incompleteCommand(command: String): Command =
    (state: State) => state.setMessage(s"$command : incomplete command!")
}

