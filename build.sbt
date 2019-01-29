
val commonSettings = Seq(
  organization := "com.lightbend.lagom",
  scalacOptions ++= List(
    "-unchecked",
    "-deprecation",
    "-language:_",
    "-encoding", "UTF-8"
  ),
  javacOptions ++= List(
    "-Xlint:unchecked",
    "-Xlint:deprecation"
  ),
  publishTo := {
    val nexus = "https://forte.jfrog.io/"
    Some("releases"  at nexus + "forte/libs-release")
   },
  organization := "co.firstfoundry.lagom",
  version := "0.0.8",
  scalaVersion := "2.12.8",
  credentials := Seq(Credentials(Path.userHome / ".sbt" / ".credentials")),
  publishMavenStyle := true
)

lazy val root = (project in file("."))
  .disablePlugins(BintrayPlugin)
  .settings(
    name := "lagom-akka-discovery-service-locator-root"
  )
  .aggregate(serviceLocatorCore, serviceLocatorJavadsl, serviceLocatorScaladsl)

lazy val serviceLocatorCore = (project in file("service-locator/core"))
  .disablePlugins(BintrayPlugin)
  .settings(commonSettings)
  .settings(
    name := "lagom-akka-discovery-service-locator-core",
    libraryDependencies ++= Dependencies.serviceLocatorCore
  )

lazy val serviceLocatorJavadsl = (project in file("service-locator/javadsl"))
  .disablePlugins(BintrayPlugin)
  .settings(commonSettings)
  .settings(
    name := "lagom-javadsl-akka-discovery-service-locator",
    libraryDependencies ++= Dependencies.serviceLocatorJavadsl
  ).dependsOn(serviceLocatorCore)

lazy val serviceLocatorScaladsl = (project in file("service-locator/scaladsl"))
  .disablePlugins(BintrayPlugin)
  .settings(commonSettings)
  .settings(
    name := "lagom-scaladsl-akka-discovery-service-locator",
    libraryDependencies ++= Dependencies.serviceLocatorScaladsl
  ).dependsOn(serviceLocatorCore)

