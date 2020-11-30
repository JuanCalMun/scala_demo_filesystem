package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.filesystem.State

trait Command {
  def apply(state: State): State

}

object Command {
  val EXIT_INPUT_COMMAND = "exit"
  val MKDIR_INPUT_COMMAND = "mkdir"

  def from(input: String): Command = {
    val tokens: Array[String] = input.split(" ")

    if (input.isEmpty || tokens.isEmpty) emptyCommand
    else tokens(0) match {
      case MKDIR_INPUT_COMMAND =>
        if (tokens.length < 2) incompleteCommand(MKDIR_INPUT_COMMAND)
        else new Mkdir(tokens(1))
      case EXIT_INPUT_COMMAND => new Exit
      case _ => new UnknownCommand
    }
  }

  def emptyCommand: Command = (state: State) => state

  def incompleteCommand(command: String): Command =
    (state: State) => state.setMessage(s"$command : incomplete command!")
}

