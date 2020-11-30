package com.rtjvm.scala.oop.filesystem

import java.util.Scanner

import com.rtjvm.scala.oop.commands.{Command, Exit}
import com.rtjvm.scala.oop.files.Directory

object Filesystem extends App {
  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (!state.output.eq(Exit.EXIT_MESSAGE)) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }
}
