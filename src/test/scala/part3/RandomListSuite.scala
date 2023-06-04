package part3

import zio.*
import zio.test.*

import RandomList.*

object RandomListSuite extends ZIOSpecDefault:

  val spec =
    suite("part3")(
      test("diferentes entradas readValidNumber") {
        for {
          _ <- TestConsole.feedLines("string", "11", "0", "5")
          num <- readValidNumber
          output <- TestConsole.output
        } yield assertTrue(num == 5) && assertTrue(output.map(_.trim) == Vector(
          "Ingrese un número entero del 1 al 10:", 
          "Entra un entero valido",
          "Ingrese un número entero del 1 al 10:", 
          "Numero fuera de rango, Introduce otro de nuevo.",
          "Ingrese un número entero del 1 al 10:",
          "Numero fuera de rango, Introduce otro de nuevo.",
          "Ingrese un número entero del 1 al 10:"
        )) 
      }
    )
