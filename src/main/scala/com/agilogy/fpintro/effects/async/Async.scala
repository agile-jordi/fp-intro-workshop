package com.agilogy.fpintro.effects.async

final case class Async[A](run: () => A) {
  def andThen[B](f: A => Async[B]): Async[B] = Async(() => f(this.run()).run())
}
