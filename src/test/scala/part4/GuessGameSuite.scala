package part4

import zio.test.*
import zio.test.Assertion.*
import java.io.IOException

object GuessGameSuite extends ZIOSpecDefault:

  val spec =
    suite("part4")(
      test("Happy path") {
        for {
          _ <- TestConsole.feedLines("50", "25", "10", "20", "15", "16")
          _ <- TestRandom.feedInts(16)
          _ <- GuessGame.program
          output <- TestConsole.output
        } yield assertTrue(output.map(_.trim) ==
            Vector(
              "Enter a maximum number to guess:",
              "Enter a number between 1 and 50:",
              "target is LOWER",
              "Enter a number between 1 and 50:",
              "target is HIGHER",
              "Enter a number between 1 and 50:",
              "target is LOWER",
              "Enter a number between 1 and 50:",
              "target is HIGHER",
              "Enter a number between 1 and 50:",
              "Congratulations, you have guessed OK"
            )
          )
      },

      test("Happy path2") {
        for {
          _ <- TestConsole.feedLines("100", "50", "25", "80", "60", "70", "65", "62", "63")
          _ <- TestRandom.feedInts(63)
          _ <- GuessGame.program
          output <- TestConsole.output
        } yield assertTrue(output.map(_.trim) ==
            Vector(
              "Enter a maximum number to guess:",
              "Enter a number between 1 and 100:",
              "target is HIGHER",
              "Enter a number between 1 and 100:",
              "target is HIGHER",
              "Enter a number between 1 and 100:",
              "target is LOWER",
              "Enter a number between 1 and 100:",
              "target is HIGHER",
              "Enter a number between 1 and 100:",
              "target is LOWER",
              "Enter a number between 1 and 100:",
              "target is LOWER",
              "Enter a number between 1 and 100:",
              "target is HIGHER",
              "Enter a number between 1 and 100:",
              "Congratulations, you have guessed OK"
            )
          )
      },

      test("Invalid input") {
        for {
          _ <- TestConsole.feedLines("str")
          result <- GuessGame.program.either
        } yield assert(result)(isLeft(isSubtype[IOException](anything)))
      }
    )