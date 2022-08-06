lazy val aktor =
  project
    .in(file("."))
    .settings(
      name := "aktor",
      version := "0.1.0-SNAPSHOT",
      organization := "scala.labs",
      scalaVersion := "2.13.8"
    )
    .settings(
      resolvers ++=
        ("Typesafe" at "https://repo.typesafe.com/typesafe/releases/") ::
          ("Java.net Maven2 Repository" at "https://download.java.net/maven/2/") ::
          Nil
    )
    .settings(
      libraryDependencies ++= modules
    )

lazy val modules =
  "org.typelevel"       %% "cats-core"        % "2.8.0" ::
    "com.typesafe.akka" %% "akka-actor-typed" % "2.6.19" ::
    "ch.qos.logback"     % "logback-classic"  % "1.2.11" ::
    Nil
