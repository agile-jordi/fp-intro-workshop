package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.domain.{Emoji, MessageContent}
import com.agilogy.fpintro.messages.infrastructure.MessagesRepository

object MessagesService {

  def addReactionsToGoodMorning(): Unit = {
    val goodMorning = MessageContent("Good mooorning FP laaand!")
    val message     = MessagesRepository.selectByContent(goodMorning)
    MessagesRepository.update(message.addReaction(Emoji.Sun).addReaction(Emoji.Tada))
  }

}
