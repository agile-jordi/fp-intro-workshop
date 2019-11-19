package com.agilogy.fpintro.effects

import com.agilogy.fpintro.effects.id.Id.Id

trait CanContinue[F[_]] {
  def continueWith[A, B](program: F[A], continuation: A => F[B]): F[B]
}

object CanContinue {

  // We move this value to the companion object of CanContinue and define it as implicit so that it is automatically
  // found by the scala compiler whenever it looks for a CanContinue[Async]
  implicit val idCanContinue: CanContinue[Id] = new CanContinue[Id] {
    override def continueWith[A, B](program: Id[A], continuation: A => Id[B]): Id[B] = continuation(program)
  }
}
