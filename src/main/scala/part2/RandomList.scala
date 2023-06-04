package part2

import zio.*

object RandomList extends ZIOAppDefault:

  val readNumber: ZIO[Any, Nothing, Int] = 
      Console.readLine("Ingrese un número entero:").orDie.flatMap { str =>
      ZIO.attempt(str.toInt).orDie
    }

  def generateList(n: Int): ZIO[Any, Nothing, List[Int]] = 
    ZIO.collectAll(List.fill(n)(Random.nextInt))


  val program: ZIO[Any, Nothing, Unit] = 
    for {
      number <- readNumber
      randomList <- generateList(number)
      _ <- Console.printLine(s"Lista: $randomList").orDie
    } yield ()
  val run = program
