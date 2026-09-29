package ch3_parameterized_queries

import cats.effect._
import ch2_query_select.Main.xa
import org.typelevel.doobie._
import org.typelevel.doobie.implicits.toSqlInterpolator

object Main extends App{
  val xa = Transactor.fromDriverManager[IO](
    driver = "org.postgresql.Driver",
    url = "jdbc:postgresql:world",
    user = "postgres",
    password = "0011",
    logHandler = None
  )

  case class Country(code: String, name: String, pop: Int, gnp: Option[Double]) // модель нашего отношения из бдешки

  val y = xa.yolo
  import y._

  val query =
    sql"select name from country"
      .query[Country] // типизация
      .stream
      .take(2)
      .quick

}