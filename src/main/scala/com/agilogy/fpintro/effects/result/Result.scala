package com.agilogy.fpintro.effects.result

sealed trait Result[+A]

object Result {
  final case class Ok[A](result: A)            extends Result[A]
  final case class Error(exception: Exception) extends Result[Nothing]

  def fromImperative[A](f: () => A): Result[A] = try { Result.Ok(f()) } catch { case e: Exception => Result.Error(e) }

}
