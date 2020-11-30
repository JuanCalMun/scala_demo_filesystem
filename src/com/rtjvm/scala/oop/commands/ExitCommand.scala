package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.commands.ExitCommand.EXIT_MESSAGE
import com.rtjvm.scala.oop.filesystem.State

import scala.util.Random

class ExitCommand extends Command {
  override def apply(state: State): State =
    state.setMessage(EXIT_MESSAGE)
}

object ExitCommand {
  val EXIT_MESSAGE: String = Random.nextString(30)
}


