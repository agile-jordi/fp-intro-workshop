# fp-intro-effects

This is a small repo of the code to be used in my workshop: You are closer to FP than you may think.

The repo is not self-explanatory, but everything should make sense after attending my workshop.

## Goal

The goal is to refactor an existing imperative program that has side effects (in this case, an innocent implementation
of persistence using a file) into 3 programs:

- The same imperative program
- A total program that encapsulates errors into the return type (instead of throwing exceptions)
- A side effect free program that returns programs that will eventually run side effects when run at the end of the
world (except that one won't handle exceptions for simplicity)

## Usage hints

This repo uses [groll](https://github.com/sbt/sbt-groll) to guide you through a step by step refactoring. To use it:

1. Clone the repository
2. Launch sbt
3. At the sbt command prompt, run `groll initial`
4. Read the code, think about next step
5. At the sbt command prompt, run `groll next`
6. Iterate from 4

If you want to see how the Async version of the program is implemented step by step you can manually checkout the 
commits in the async branch by hand. Otherwise you'll se all of them in a merge commit when you `groll next` after the 
Result implementation.

## Disclaimer

The abstractions implemented here have the sole purpose of teaching functional programming. In particular, some aspects
that I considered out of the scope of this workshop should NOT be left out of any professional implementation:

- Typeclasses should have laws, so that you can reason about what they abstract over
- The implementations of `Result` and `Async` should be stack safe, so that you don't blow the stack when you run a
long chain of `flatMap`s.
- The implementations given here don't care at all about performance. Having an unperformant Monad like this can lead to
performance problems in real programs.
