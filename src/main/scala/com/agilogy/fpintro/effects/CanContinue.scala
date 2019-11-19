package com.agilogy.fpintro.effects

trait CanContinue[F[_]] {
  def continueWith[A, B](program: F[A], continuation: A => F[B]): F[B]
}
