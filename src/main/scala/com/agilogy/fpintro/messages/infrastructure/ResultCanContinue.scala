package com.agilogy.fpintro.messages.infrastructure

import com.agilogy.fpintro.effects.result.Result
import com.agilogy.fpintro.messages.app.CanContinue

object ResultCanContinue {

  val resultCanContinue: CanContinue[Result] = new CanContinue[Result] {
    override def continueWith[A, B](program: Result[A], continuation: A => Result[B]): Result[B] =
      program.ifOk(continuation)
  }
}
