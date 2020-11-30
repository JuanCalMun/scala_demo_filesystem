package com.rtjvm.scala.oop.filesystem

import java.util.Scanner

import com.rtjvm.scala.oop.filesystem.State.SHELL_TOKEN

object Filesystem extends App {
  val scanner = new Scanner(System.in)
  while (true) {
    print(SHELL_TOKEN)
    println(scanner.nextLine())
  }
}
