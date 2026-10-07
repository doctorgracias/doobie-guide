package ch4_insert_update_delete.dao

import org.typelevel.doobie._
import org.typelevel.doobie.implicits._
import org.typelevel.doobie.util.ExecutionContexts
import cats._
import cats.data._
import cats.effect._
import cats.implicits._
import cats.effect.unsafe.implicits.global

trait CountryRepository {
  def insert(country : Country)(implicit xa : Transactor[IO]) : IO[Int] // ConnectionIO возвращает кол - во строк после .run
  def update(code : String, newName : String)(implicit xa : Transactor[IO]) : IO[Int]
  def delete(code : String)(implicit xa : Transactor[IO]) : IO[Int]
}

object CountryRepository extends CountryRepository {
  def insert(country : Country)(implicit xa : Transactor[IO]) = {
    sql"insert into country (code, name, population, gnp) values (${country.code},${country.name},${country.population},${country.gnp})"
      .update //вместо querry просто юзаем update
      .run
      .transact(xa)
  }

  def update(code : String, newName : String)(implicit xa : Transactor[IO]) = {
    sql"update country set name = ${newName}  where country.code = ${code}"
      .update
      .run
      .transact(xa)
  }

  override def delete(code: String)(implicit xa : Transactor[IO]): IO[Int] = {
    sql"delete country where name = ${code}"
      .update
      .run
      .transact(xa)
  }
}
