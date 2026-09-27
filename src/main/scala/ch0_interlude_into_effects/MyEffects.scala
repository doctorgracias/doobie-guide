package ch0_interlude_into_effects

import cats.effect.IO
import cats.effect.unsafe.implicits.global // заглушка чтобы запускать эффекты

object MyEffects extends App{
  //вот и функция которая возвращает эффект
  //IO это такой же контейнер как и Option или Either
  //IO == "может быть эффект во внешнем мире" ОПРЕДЕЛЯЕТ ЭФФЕКТ ОТ ЕГО ВЫПОЛЕНИЯ
  val printInfo = (str : String) => IO[Unit] {
    println(str)
  }

  val num42 : IO[Int] = IO.pure(42)

  //А ВОТ И ВЫПОЛНЕНИЕ
  printInfo("Hello world !")
    .unsafeRunSync()

  // А ЭТО УЖЕ КОМПОЗИЦИЯ ЭФФЕКТОВ!
  val composableEffects = for {
    num <- num42 // вычеслили значение прямо в цепочке фора (ну или мапа если хотите) и передали его контекст этого фора
    _ <- printInfo(s"Hello amerima I am a $num!")
  } yield ()

  val chainedEffects =
    printInfo("hola") *> printInfo(" sup!")

  composableEffects
    .unsafeRunSync()
}
