package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.domain.{Emoji, MessageContent}

final class ImperativeMessagesService(repository: ImperativeMessagesRepository) {

  def addReactionsToGoodMorning(): Unit = {
    val goodMorning = MessageContent("Good mooorning FP laaand!")
    val message     = repository.selectByContent(goodMorning)
    repository.update(message.addReaction(Emoji.Sun).addReaction(Emoji.Tada))
  }

}
