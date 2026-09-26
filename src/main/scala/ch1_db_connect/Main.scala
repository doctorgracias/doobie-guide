package ch1_db_connect

import cats.effect._
import cats.effect.unsafe.implicits.global
import cats.implicits._
import org.typelevel.doobie._
import org.typelevel.doobie.implicits._

object Main extends App
{
  val program1 = 42.pure[ConnectionIO]

  val xa = Transactor.fromDriverManager[IO](
    driver = "org.postgresql.Driver",  // JDBC driver classname
    url = "jdbc:postgresql:world",     // Connect URL
    user = "postgres",                 // Database user name
    password = "0011",                 // Database password
    logHandler = None                  // Don't setup logging for now. See Logging page for how to log events in detail
  )


  val a = for {
    a <- sql"select population from world where name = 'United Kingdom'".query[Int].unique
  }yield a

  println(a.transact(xa).unsafeRunSync())
}