package part1

import zio.test.*
import zio.test.Assertion.*
import MyZIO.*
import zio.*

object MyZIOSuite extends ZIOSpecDefault:

  val spec = suite("part1")(
    test("Ambas pasan") {
      val op1 = succeed(1)
      val op2 = succeed(2)
      assert(op1.orElse(op2).run(()))(equalTo(Right(1)))
    },
    test("Primero pasa y el segundo no") {
      val op1 = succeed(1)
      val op2 = fail(-5)
      assert(op1.orElse(op2).run(()))(equalTo(Right(1)))
    },
    test("Primero falla y el segundo no") {
      val op1 = fail(-5)
      val op2 = succeed(1)
      assert(op1.orElse(op2).run(()))(equalTo(Right(1)))
    },
    test("Los 2 fallan") {
      val op1 = fail(-4)
      val op2 = fail(-5)
      assert(op1.orElse(op2).run(()))(equalTo(Left(-5)))
    }
  )
