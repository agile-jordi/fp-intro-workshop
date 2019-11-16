package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.domain.{Message, MessageContent}

trait MessagesRepository[F[_]] {
  def selectByContent(content: MessageContent): F[Message]
  def update(message: Message): F[Unit]
}
