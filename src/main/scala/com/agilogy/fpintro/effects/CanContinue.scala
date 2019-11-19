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

object CanContinueSyntax {

  // An implicit class adds methods to an existing type. In this particular case it adds methods to any type F[A] as
  // long as we have an implicit instance of type `CanContinue[F]`.
  implicit class CanContinueSyntaxOps[F[_], A](self: F[A])(implicit canContinue: CanContinue[F]) {
    def flatMap[B](f: A => F[B]): F[B] = canContinue.flatMap(self, f)
    def map[B](f: A => B): F[B]        = canContinue.map(self, f)
  }

}
