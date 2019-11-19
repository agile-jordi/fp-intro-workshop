package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.effects.id.Id
import com.agilogy.fpintro.effects.id.Id.Id
import com.agilogy.fpintro.messages.infrastructure.ImperativeMessagesRepository

object ImperativeApplication {

  private val repository: MessagesRepository[Id] = ImperativeMessagesRepository
  private val service: MessagesService[Id]       = new MessagesService[Id](repository, Id.idCanContinue)

  def main(args: Array[String]): Unit = service.addReactionsToGoodMorning()

}
