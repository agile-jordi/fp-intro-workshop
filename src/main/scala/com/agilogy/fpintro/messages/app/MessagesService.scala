package com.agilogy.fpintro.messages.app

trait MessagesService[F[_]] {

  def addReactionsToGoodMorning(): F[Unit]
}
