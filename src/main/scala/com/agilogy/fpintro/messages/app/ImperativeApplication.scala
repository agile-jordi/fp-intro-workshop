package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.infrastructure.ImperativeMessagesRepository

object ImperativeApplication {

  private val repository: ImperativeMessagesRepository = ImperativeMessagesRepository
  private val service: ImperativeMessagesService       = new ImperativeMessagesService(repository)

  def main(args: Array[String]): Unit = service.addReactionsToGoodMorning()

}
