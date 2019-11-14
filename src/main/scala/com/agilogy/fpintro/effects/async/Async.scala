package com.agilogy.fpintro.effects.async

final case class Async[A](run: () => A)
