package com.agilogy.fpintro.effects.id

import com.agilogy.fpintro.effects.CanContinue

object Id {
  // A type alias of a type x that results in x itself.
  type Id[x] = x

  val idCanContinue: CanContinue[Id] = new CanContinue[Id] {
    override def continueWith[A, B](program: Id[A], continuation: A => Id[B]): Id[B] = continuation(program)
  }
}
