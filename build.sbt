ThisBuild / scalaVersion := "2.12.10"
ThisBuild / organization := "com.agilogy"

lazy val fpEffectsWorkshop = (project in file(".")).settings(
  name := "fp-effects-workshop",
  scalaVersion := "2.12.10",
  scalacOptions ++= List("-Ypartial-unification", "-Yrangepos", "-Ywarn-unused-import"),
  addCompilerPlugin(scalafixSemanticdb),
  libraryDependencies += "org.typelevel" %% "cats-core" % "2.0.0",
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
)

addCommandAlias("fix", "all compile:scalafix test:scalafix")
