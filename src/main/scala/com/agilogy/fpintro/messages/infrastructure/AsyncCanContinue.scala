package com.agilogy.fpintro.messages.infrastructure

import com.agilogy.fpintro.effects.async.Async
import com.agilogy.fpintro.messages.app.CanContinue

object AsyncCanContinue {

  val asyncCanContinue: CanContinue[Async] = new CanContinue[Async] {
    override def continueWith[A, B](program: Async[A], continuation: A => Async[B]): Async[B] =
      program.andThen(continuation)
  }
}
