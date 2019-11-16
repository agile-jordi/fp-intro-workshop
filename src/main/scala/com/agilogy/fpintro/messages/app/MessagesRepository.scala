package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.domain.{Message, MessageContent}

trait MessagesRepository {
  def selectByContent(content: MessageContent): Message
  def update(message: Message): Unit
}
