package com.agilogy.fpintro.effects.result

import com.agilogy.fpintro.effects.Monad

sealed trait Result[+A] {

  def unsafeGet(): A = this match {
    case Result.Ok(a)    => a
    case Result.Error(e) => throw e
  }

  def ifOk[B](f: A => Result[B]): Result[B] = this match {
    case Result.Ok(a)    => f(a)
    case Result.Error(e) => Result.Error(e)
  }
}

object Result {
  final case class Ok[A](result: A)            extends Result[A]
  final case class Error(exception: Exception) extends Result[Nothing]

  def fromImperative[A](f: () => A): Result[A] = try { Result.Ok(f()) } catch { case e: Exception => Result.Error(e) }

  // We define this value as implicit so that it is automatically found by the scala compiler whenever it looks for
  // a Monad[Result]
  implicit val resultMonad: Monad[Result] = new Monad[Result] {
    override def flatMap[A, B](program: Result[A], continuation: A => Result[B]): Result[B] =
      program.ifOk(continuation)
    override def pure[A](value: A): Result[A] = Result.Ok(value)
  }
}
