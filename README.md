# Doobie Guide

> Мой вариант официальной документации [Doobie](https://typelevel.org/doobie/) —

## Что такое Doobie?

**Doobie** — это не ORM и не реляционная алгебра.  
Это функциональный способ работать с JDBC в Scala, построенный поверх **Cats** и **Cats Effect**.

```scala
sql"select name from country"
  .query[String]
  .stream
  .take(5)
  .transact(xa)
```

## Стек

- **Scala** 2.13.14
- **Doobie** 1.0.0-RC13
- **Cats Effect** 3.x
- **PostgreSQL**
