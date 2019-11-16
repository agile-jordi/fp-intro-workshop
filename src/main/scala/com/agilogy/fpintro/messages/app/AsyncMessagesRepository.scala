package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.effects.async.Async
import com.agilogy.fpintro.messages.domain.{Message, MessageContent}

trait AsyncMessagesRepository {
  def selectByContent(content: MessageContent): Async[Message]
  def update(message: Message): Async[Unit]
}
