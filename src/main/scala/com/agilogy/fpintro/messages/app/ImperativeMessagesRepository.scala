package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.domain.{Message, MessageContent}

trait ImperativeMessagesRepository {
  def selectByContent(content: MessageContent): Message
  def update(message: Message): Unit
}
