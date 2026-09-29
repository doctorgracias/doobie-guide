package ch2_query_select

import cats.effect._
import org.typelevel.doobie._
import org.typelevel.doobie.implicits._

import cats.effect.unsafe.implicits.global

object Main extends App{
  val xa = Transactor.fromDriverManager[IO](
    driver = "org.postgresql.Driver",
    url = "jdbc:postgresql:world",
    user = "postgres",
    password = "0011",
    logHandler = None
  )

  val y = xa.yolo
  import y._   // pimp my library паттерн  - реализация через имплисит классы

  val query =
    sql"select name from country"
      .query[String]
      .stream
      .take(2)
      .quick // вот и наш метод из y._ который взялся из исплисит класса для Stream - заменяет боилплейт из транзакта и фор ича с принтом
}