package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.effects.async.Async
import com.agilogy.fpintro.messages.domain.{Emoji, MessageContent}

final class AsyncMessagesService(repository: AsyncMessagesRepository) {

  def addReactionsToGoodMorning(): Async[Unit] = {
    val goodMorning = MessageContent("Good mooorning FP laaand!")
    repository.selectByContent(goodMorning).andThen { message =>
      repository.update(message.addReaction(Emoji.Sun).addReaction(Emoji.Tada))
    }
  }

}
