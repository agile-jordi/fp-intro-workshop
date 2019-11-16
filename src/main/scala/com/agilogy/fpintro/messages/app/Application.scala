package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.messages.infrastructure.MessagesRepository

object Application {

  private val repository: MessagesRepository = MessagesRepository
  def main(args: Array[String]): Unit        = MessagesService.addReactionsToGoodMorning(repository)

}
