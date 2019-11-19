package com.agilogy.fpintro.messages.app

import com.agilogy.fpintro.effects.Monad
import com.agilogy.fpintro.effects.async.Async
import com.agilogy.fpintro.effects.id.Id.Id
import com.agilogy.fpintro.effects.result.Result
import com.agilogy.fpintro.messages.infrastructure.{
  AsyncMessagesRepository,
  ImperativeMessagesRepository,
  ResultMessagesRepository
}

abstract class Application[F[_]](repository: MessagesRepository[F])(implicit M: Monad[F]) {

  private val service: MessagesService[F] = new MessagesService(repository)

  def run[A](program: F[A]): A

  def main(args: Array[String]): Unit = run(service.addReactionsToGoodMorning())

}

// These 2 applications take an implicit Monad. The implicit is found because it is defined as a `val` of the
// type we replace the `F` for (Async / Result). That is, when searching for an implicit value of type
// `Monad[Async]` the scala compiler also looks in the companion object of `Async`, where it finds it.

object AsyncApplication extends Application[Async](AsyncMessagesRepository) {
  override def run[A](program: Async[A]): A = program.run()
}

object ResultApplication extends Application[Result](ResultMessagesRepository) {
  override def run[A](program: Result[A]): A = program.unsafeGet()
}

// Id is a type alias. For the compiler to find the implicit automatically we can't place it in the object Id that
// contains the type alias. So we moved it to Monad. That is, when searching for an implicit value of type
// `Monad[Id]` the scala compiler also looks in the companion object of `Monad`, where it finds it.

object ImperativeApplication extends Application[Id](ImperativeMessagesRepository) {
  override def run[A](program: Id[A]): A = program
}
