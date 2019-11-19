package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.domain.{Emoji, Message, MessageContent}
import com.agilogy.fpintro.messages.infrastructure.{
  AsyncCanContinue,
  AsyncMessagesRepository,
  ResultCanContinue,
  ResultMessagesRepository
}

trait CanContinue[F[_]] {
  def continueWith[A, B](program: F[A], continuation: A => F[B]): F[B]
}

final class MessagesService[F[_]](repository: MessagesRepository[F], canContinue: CanContinue[F]) {

  def addReactionsToGoodMorning(): F[Unit] = {
    val goodMorning   = MessageContent("Good mooorning FP laaand!")
    val selectMessage = repository.selectByContent(goodMorning)
    val updateMessage = (m: Message) => repository.update(m.addReaction(Emoji.Sun).addReaction(Emoji.Tada))
    canContinue.continueWith(selectMessage, updateMessage)
  }

}

object MessagesService {
  // Example instances:
  val asyncMessagesService  = new MessagesService(AsyncMessagesRepository, AsyncCanContinue.asyncCanContinue)
  val resultMessagesService = new MessagesService(ResultMessagesRepository, ResultCanContinue.resultCanContinue)
}
