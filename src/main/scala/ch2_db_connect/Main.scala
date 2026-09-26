package ch2_db_connect

import cats.effect._
import org.typelevel.doobie._
import org.typelevel.doobie.implicits._

// This is just for testing. Consider using cats.effect.IOApp instead of calling
// unsafe methods directly.
import cats.effect.unsafe.implicits.global

// A transactor that gets connections from java.sql.DriverManager and executes blocking operations
// on our synchronous EC. See the chapter on connection handling for more info.
object Main extends App{
  val xa = Transactor.fromDriverManager[IO](
    driver = "org.postgresql.Driver",  // JDBC driver classname
    url = "jdbc:postgresql:world",     // Connect URL
    user = "postgres",                 // Database user name
    password = "0011",             // Database password
    logHandler = None                  // Don't setup logging for now. See Logging page for how to log events in detail
  )

  val y = xa.yolo


  case class Country()

  val query1 =
    sql"select name from country"
      .query[String]
      .to[List]
      .transact(xa)
      .unsafeRunSync()
      .foreach(x => print(x + " "))

  println()

  val query2 =
    sql"select name from country"
      .query[String]
      .stream
      .take(2)
      .transact(xa)
      .compile.toList
      .unsafeRunSync()
      .foreach(x => print(x + " "))
}