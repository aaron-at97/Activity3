package part4

import zio.*
import java.io.IOException

object GuessGame extends ZIOAppDefault:

  def loop(rnd: Int, max: Int): ZIO[Any, IOException, Unit] =
    for
      str <- Console.readLine(s"Enter a number between 1 and $max: ")
      num <- ZIO.attempt(str.toInt).orElseFail(new IOException("Formato incorrecto"))
      _ <-
        if (num == rnd) 
          Console.printLine("Congratulations, you have guessed OK ").orDie
        else if (num > rnd) 
          Console.printLine("target is LOWER ").orDie *> loop(rnd, max)
        else 
          Console.printLine("target is HIGHER ").orDie *> loop(rnd, max)
    yield () 

  val program: ZIO[Any, IOException, Unit] =
    for
      str <- Console.readLine("Enter a maximum number to guess: ")
      num <- ZIO.attempt(str.toInt).orElseFail(new IOException("Formato incorrecto"))
      rnd <- ZIO.attempt(Random.nextIntBetween(1, num)).orDie
      rndnum <- rnd
      _ <- loop(rndnum, num)
    yield ()

  val run = program
