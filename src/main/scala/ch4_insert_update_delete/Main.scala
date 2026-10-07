package ch4_insert_update_delete

import cats.effect._
import ch4_insert_update_delete.dao.{Country, CountryRepository}
import org.typelevel.doobie._
import org.typelevel.doobie.implicits._
import cats.effect.unsafe.implicits.global


object Main extends App{
  implicit val xa = Transactor.fromDriverManager[IO](
    driver = "org.postgresql.Driver",
    url = "jdbc:postgresql:world",
    user = "postgres",
    password = "0011",
    logHandler = None
  )

  val repo = CountryRepository
  val portugal : Country = Country("PG","Portugal",12,None)


  repo.insert(portugal).unsafeRunSync()

}