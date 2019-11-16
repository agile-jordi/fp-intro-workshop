package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.effects.result.Result
import com.agilogy.fpintro.messages.domain.{Message, MessageContent}

trait ResultMessagesRepository extends MessagesRepository[Result] {
  def selectByContent(content: MessageContent): Result[Message]
  def update(message: Message): Result[Unit]
}
