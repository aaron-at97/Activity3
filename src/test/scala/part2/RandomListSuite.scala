package part2

import zio.*
import zio.test.*

import zio.Exit.*
import RandomList.*

object RandomListSuite extends ZIOSpecDefault:
  val spec =
    suite("part2")(
    test("readNumber success") {
      for {
        _ <- TestConsole.feedLines("7") // Simulamos la entrada de datos
        n <- readNumber
        input <- TestConsole.output
      } yield assertTrue(n == 7) && assertTrue(input == Vector("Ingrese un número entero:"))
    },
    test("lista con tamaño correcto") {
      for {
        _ <- TestRandom.feedInts(1, 2, 3, 4, 5)
        list <- generateList(5) 
      } yield assertTrue(list.length == 5) 
    },
    test("lista con un contenido correcto") {
      for {
        _ <- TestRandom.feedInts(8, 5, 4, 3, 2) 
        list <- generateList(5) 
      } yield assertTrue(list == List(8, 5, 4, 3, 2))
    },
    test("program salida correcta") {
      for {
        _ <- TestConsole.feedLines("5")
        _ <- TestRandom.feedInts(8, 5, 4, 3, 2)
        _ <- program
        output <- TestConsole.output
      } yield assertTrue(output == Vector("Ingrese un número entero:", "Lista: List(8, 5, 4, 3, 2)\n"))
    }
  )