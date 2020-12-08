package com.rtjvm.scala.oop.commands

import com.rtjvm.scala.oop.files.DirEntry
import com.rtjvm.scala.oop.filesystem.State

class Ls extends Command {
  override def apply(state: State): State = {
    state.setMessage(beautifyOutput(state.workingDirectory.contents))
  }

  def beautifyOutput(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else {
      val entry = contents.head
      val icon = if (entry.getType.eq("Directory")) "📁" else "📝"
      s"\t $icon ${entry.name}\n".concat(beautifyOutput(contents.tail))
    }
  }
}




