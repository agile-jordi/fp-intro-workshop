package com.agilogy.fpintro.effects

import com.agilogy.fpintro.effects.id.Id.Id

trait Monad[F[_]] {
  def flatMap[A, B](program: F[A], continuation: A => F[B]): F[B]
  def pure[A](value: A): F[A]
  def map[A, B](program: F[A], f: A => B): F[B] = flatMap(program, (a: A) => pure(f(a)))
}

object Monad {

  // This method allows us to write Monad[F] instead of implicitly[Monad[F]]
  def apply[F[_]](implicit M: Monad[F]): Monad[F] = M

  // We move this value to the companion object of Monad and define it as implicit so that it is automatically
  // found by the scala compiler whenever it looks for a Monad[Async]
  implicit val idMonad: Monad[Id] = new Monad[Id] {
    override def flatMap[A, B](program: Id[A], continuation: A => Id[B]): Id[B] = continuation(program)
    override def pure[A](value: A): Id[A]                                       = value
  }
}

object MonadSyntax {

  // An implicit class adds methods to an existing type. In this particular case it adds methods to any type F[A] as
  // long as we have an implicit instance of type `Monad[F]`.
  implicit class MonadSyntaxOps[F[_]: Monad, A](self: F[A]) {
    // See `Monad.apply[F[_]]`
    def flatMap[B](f: A => F[B]): F[B] = Monad[F].flatMap(self, f)
    def map[B](f: A => B): F[B]        = Monad[F].map(self, f)
  }

}
