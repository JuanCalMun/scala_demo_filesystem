package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.commands.Exit.EXIT_MESSAGE
import com.rtjvm.scala.oop.filesystem.State

import scala.util.Random

class Exit extends Command {
  override def apply(state: State): State =
    state.setMessage(EXIT_MESSAGE)
}

object Exit {
  val EXIT_MESSAGE: String = Random.nextString(30)
}


