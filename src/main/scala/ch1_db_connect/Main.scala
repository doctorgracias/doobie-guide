package ch1_db_connect

import cats.effect._
import cats.effect.unsafe.implicits.global
import cats.implicits._
import org.typelevel.doobie._
import org.typelevel.doobie.implicits._


// для начала работы выполни tables.sql в субд

object Main extends App
{
  val xa = Transactor.fromDriverManager[IO]( // подключение к БД + пулы соединения
    driver = "org.postgresql.Driver",  // имя JDBC драйверва - в нашем случае у нас postgresql
    url = "jdbc:postgresql:world",     // хост нашей бдешки - локальной
    user = "postgres",                 // имя
    password = "0011",                 // пароль
    logHandler = None                  // логгер нам пока что не нужен
  )

  // это нам вернет эффект транзакции в базе данных, далее эту транзакцию мы передадим транзактору "xa"
  val a = for {
    a <- sql"select population from world where name = 'United Kingdom'" //интерполятор sql НУЖЕН ТОЛЬКО ДЛЯ ПРЕДОСТАВЛЕНИЯ САМОГО ЗАПРОСА
      .query[Int] // НУЖЕН ДЛЯ КОРРЕТНОГО МАППИНГА СТРОКИ В НУЖНЫЙ ТИП, НО НИЧЕГО НЕ ЗНАЕТ О КОЛ - ВЕ СТРОК ЧТО НАМ НУЖНО ПОЛУЧИТЬ
      .unique // НУЖЕН ДЛЯ УКАЗАНИЯ КОЛ-ВА СТРОК - альтернативы .option (одну) .to[List] (список всех строк) .stream (поток - разберем дальше) .nel(not empty list)
  }yield a

  val b = for {
    b <- sql"select population from world where name = 'United Kingdom'"
      .query[Int]
      .stream // на этот раз откроем поток
      .take(5) // из потока возьмем лишь 5 записей
  }yield b

  //выполняем
  println
  (a // или же b
    .transact(xa)
  )
}