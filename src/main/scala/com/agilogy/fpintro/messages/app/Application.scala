package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.effects.CanContinue
import com.agilogy.fpintro.effects.async.Async
import com.agilogy.fpintro.effects.id.Id
import com.agilogy.fpintro.effects.id.Id.Id
import com.agilogy.fpintro.effects.result.Result
import com.agilogy.fpintro.messages.infrastructure.{
  AsyncMessagesRepository,
  ImperativeMessagesRepository,
  ResultMessagesRepository
}

abstract class Application[F[_]](repository: MessagesRepository[F], canContinue: CanContinue[F]) {

  private val service: MessagesService[F] = new MessagesService(repository, canContinue)

  def run[A](program: F[A]): A

  def main(args: Array[String]): Unit = run(service.addReactionsToGoodMorning())

}

object AsyncApplication extends Application[Async](AsyncMessagesRepository, Async.asyncCanContinue) {
  override def run[A](program: Async[A]): A = program.run()
}

object ResultApplication extends Application[Result](ResultMessagesRepository, Result.resultCanContinue) {
  override def run[A](program: Result[A]): A = program.unsafeGet()
}

object ImperativeApplication extends Application[Id](ImperativeMessagesRepository, Id.idCanContinue) {
  override def run[A](program: Id[A]): A = program
}
