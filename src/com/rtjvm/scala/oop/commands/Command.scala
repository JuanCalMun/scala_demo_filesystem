package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.filesystem.State

trait Command {
  def apply(state: State): State
}

object Command {
  def from(input: String): Command = {
    input match {
      case "exit" => new ExitCommand
      case _ => new UnknownCommand
    }
  }
}
