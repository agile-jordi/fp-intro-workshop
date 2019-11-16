package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.infrastructure.MessagesRepository

object Application {

  private val repository: MessagesRepository = MessagesRepository
  private val service: MessagesService       = new MessagesService(repository)

  def main(args: Array[String]): Unit = service.addReactionsToGoodMorning()

}
