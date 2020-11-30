package com.rtjvm.scala.oop.filesystem

import java.util.Scanner

object Filesystem extends App {
  val scanner = new Scanner(System.in)
  while (true) {
    print(State.SHELL_TOKEN)
    println(scanner.nextLine())
  }
}
