package com.agilogy.fpintro.messages.imperative

import com.agilogy.fpintro.messages.domain.{Emoji, MessageContent}
import com.agilogy.fpintro.messages.infrastructure.MessagesRepository

object Application {

  def main(args: Array[String]): Unit = {
    val goodMorning = MessageContent("Good mooorning FP laaand!")
    val message     = MessagesRepository.selectByContent(goodMorning)
    MessagesRepository.update(message.addReaction(Emoji.Sun).addReaction(Emoji.Tada))
  }

}
