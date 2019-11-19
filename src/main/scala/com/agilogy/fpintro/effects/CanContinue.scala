package com.agilogy.fpintro.effects

import com.agilogy.fpintro.effects.id.Id.Id

trait CanContinue[F[_]] {
  def flatMap[A, B](program: F[A], continuation: A => F[B]): F[B]
  def asCanContinue[A](value: A): F[A]
  def map[A, B](program: F[A], f: A => B): F[B] = flatMap(program, (a: A) => asCanContinue(f(a)))
}

object CanContinue {

  // We move this value to the companion object of CanContinue and define it as implicit so that it is automatically
  // found by the scala compiler whenever it looks for a CanContinue[Async]
  implicit val idCanContinue: CanContinue[Id] = new CanContinue[Id] {
    override def flatMap[A, B](program: Id[A], continuation: A => Id[B]): Id[B] = continuation(program)
    override def asCanContinue[A](value: A): Id[A]                              = value
  }
}
