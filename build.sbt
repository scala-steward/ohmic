import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.nigeleke"
ThisBuild / organizationName := "nigel-eke"

val poiVersion = "4.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "resistors",
    libraryDependencies ++= Seq(
      "org.apache.poi" % "poi" % poiVersion,
      "org.apache.poi" % "poi-ooxml" % poiVersion,
      "org.typelevel"  %% "squants"  % "1.6.0",
      scalaTest % Test
    )
  )
