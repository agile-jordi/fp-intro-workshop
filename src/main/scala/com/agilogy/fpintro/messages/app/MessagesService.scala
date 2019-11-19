package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.effects.CanContinue
import com.agilogy.fpintro.effects.async.Async
import com.agilogy.fpintro.effects.result.Result
import com.agilogy.fpintro.messages.domain.{Emoji, Message, MessageContent}
import com.agilogy.fpintro.messages.infrastructure.{AsyncMessagesRepository, ResultMessagesRepository}

final class MessagesService[F[_]](repository: MessagesRepository[F], canContinue: CanContinue[F]) {

  def addReactionsToGoodMorning(): F[Unit] = {
    val goodMorning   = MessageContent("Good mooorning FP laaand!")
    val selectMessage = repository.selectByContent(goodMorning)
    val updateMessage = (m: Message) => repository.update(m.addReaction(Emoji.Sun).addReaction(Emoji.Tada))
    canContinue.continueWith(selectMessage, updateMessage)
  }

}
