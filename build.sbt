ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

val zioHttpVersion = "3.0.0-RC10"
val circeVersion   = "0.14.9"
val jwtScalaVersion = "10.0.1"
val doobieVersion = "1.0.0-RC5"
val catsVersion = "2.12.0"

libraryDependencies ++= Seq(
  "dev.zio" %% "zio-http" % zioHttpVersion,

  "io.circe" %% "circe-core"    % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser"  % circeVersion,

  "com.github.jwt-scala" %% "jwt-core"  % jwtScalaVersion,
  "com.github.jwt-scala" %% "jwt-circe" % jwtScalaVersion,

  "dev.zio" %% "zio-interop-cats" % "23.1.0.3",
)

libraryDependencies ++= Seq(

  // Start with this one
  "org.typelevel" %% "doobie-core"      % "1.0.0-RC13",

  // And add any of these as needed
  "org.typelevel" %% "doobie-postgres"  % "1.0.0-RC13",          // Postgres driver 42.7.10 + type mappings.
  "org.typelevel" %% "doobie-specs2"    % "1.0.0-RC13" % "test", // Specs2 support for typechecking statements.
  "org.typelevel" %% "doobie-scalatest" % "1.0.0-RC13" % "test"  // ScalaTest support for typechecking statements.

)

testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")

lazy val root = (project in file("."))
  .settings(
    name := "doobie-guide"
  )

