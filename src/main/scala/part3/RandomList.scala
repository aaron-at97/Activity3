package part3

import zio.*

object RandomList extends ZIOAppDefault:

  val readValidNumber: ZIO[Any, Nothing, Int] = 
    for {
      num <- Console.readLine("Ingrese un número entero del 1 al 10:").orDie.flatMap { str =>
        ZIO.attempt(str.toInt).orElse(
        Console.printLine("Entra un entero valido").orDie *> readValidNumber)
      }
      validNumber  <- 
        if (num >= 1 && num <= 10) ZIO.succeed(num)
        else 
          Console.printLine("Numero fuera de rango, Introduce otro de nuevo.").orDie *> readValidNumber
    } yield validNumber

  def generateList(n: Int): ZIO[Any, Nothing, List[Int]] = 
    ZIO.collectAll(List.fill(n)(Random.nextInt))

  val program: ZIO[Any, Nothing, Unit] = 
    for {
      number <- readValidNumber
      randomList <- generateList(number)
      _ <- Console.printLine(s"Lista: $randomList").orDie
    } yield ()
  val run = program
