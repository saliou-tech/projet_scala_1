name := """todolist"""
organization := "test"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.3"

// libraryDependencies += "com.typesafe.play" %% "play-slick" % "4.0.0"
// libraryDependencies ++= Seq(
//   "com.typesafe.play" %% "play-slick" % "4.0.0",
//   "com.typesafe.play" %% "play-slick-evolutions" % "4.0.0"
// )

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"
libraryDependencies += "com.typesafe.play" %% "play-slick" % "4.0.2"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "4.0.2" 

//database connectiity
// Default database configuration using MySQL database engine
// Connect to scalatestdb as testuser


// Adds additional packages into Twirl
//TwirlKeys.templateImports += "test.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "test.binders._"
