package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.effects.CanContinue
import com.agilogy.fpintro.messages.domain.{Emoji, Message, MessageContent}
import com.agilogy.fpintro.effects.CanContinueSyntax._

final class MessagesService[F[_]](repository: MessagesRepository[F])(implicit canContinue: CanContinue[F]) {

  def addReactionsToGoodMorning(): F[Unit] = {
    val goodMorning = MessageContent("Good mooorning FP laaand!")

    // We can now use `flatMap` like it was a method of type F[Unit]. Note that F[Unit] does not have any method except
    // those in Any (that we don't want to use anyway) and those added by some syntax (see CanContinueSyntax).
    // In case of getting lost by implicits, remember to use View > Show implicit hints in Intellij (and View ->
    // Expand Implicit Hints if needed)
    repository
      .selectByContent(goodMorning)
      .flatMap(m => repository.update(m.addReaction(Emoji.Sun).addReaction(Emoji.Tada)))
  }

}
