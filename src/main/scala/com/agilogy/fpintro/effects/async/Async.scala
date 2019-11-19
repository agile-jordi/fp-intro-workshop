package com.agilogy.fpintro.effects.async

import com.agilogy.fpintro.effects.Monad

final case class Async[A](run: () => A) {
  def andThen[B](f: A => Async[B]): Async[B] = Async(() => f(this.run()).run())
}

object Async {

  // We define this value as implicit so that it is automatically found by the scala compiler whenever it looks for
  // a Monad[Async]
  implicit val asyncMonad: Monad[Async] = new Monad[Async] {
    override def flatMap[A, B](program: Async[A], continuation: A => Async[B]): Async[B] =
      program.andThen(continuation)
    override def pure[A](value: A): Async[A] = Async(() => value)
  }
}
