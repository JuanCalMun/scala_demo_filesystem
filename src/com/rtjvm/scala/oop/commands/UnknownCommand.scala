package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.commands.UnknownCommand.NOT_FOUND_MESSAGE
import com.rtjvm.scala.oop.filesystem.State

class UnknownCommand extends Command {
  override def apply(state: State): State =
    state.setMessage(NOT_FOUND_MESSAGE)
}

object UnknownCommand {
  val NOT_FOUND_MESSAGE = "Command not found!"
}
