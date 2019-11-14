package com.agilogy.fpintro.users.imperative

import com.agilogy.fpintro.users.domain.{Emoji, MessageContent}
import com.agilogy.fpintro.users.infrastructure.MessagesRepository

object Application {

  def main(args: Array[String]): Unit = {
    val goodMorning = MessageContent("Good mooorning FP laaand!")
    val message     = MessagesRepository.selectByContent(goodMorning).get
    MessagesRepository.update(message.addReaction(Emoji.Sun).addReaction(Emoji.Tada))

  }

}
