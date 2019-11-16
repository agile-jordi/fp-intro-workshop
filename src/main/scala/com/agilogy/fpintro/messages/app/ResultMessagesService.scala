package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.domain.{Emoji, MessageContent}

final class ResultMessagesService(repository: ResultMessagesRepository) {

  def addReactions(): Unit = {
    val goodMorning = MessageContent("Good mooorning FP laaand!")
    repository.selectByContent(goodMorning).ifOk { message =>
      repository.update(message.addReaction(Emoji.Sun).addReaction(Emoji.Tada))
    }
  }
}
