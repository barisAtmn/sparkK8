name := "SparkK8"

version := "0.1"

scalaVersion := "2.12.12"

import Dependencies._

libraryDependencies ++= sparkLibraries
libraryDependencies ++= loggingLibraries
libraryDependencies ++= delta
libraryDependencies += "com.datastax.spark" %% "spark-cassandra-connector" % "3.0.0"
libraryDependencies += "com.github.jnr" % "jnr-posix" % "3.1.4"

fork in run := true
javaOptions in run ++= Seq(
  "-Dlog4j.debug=true",
  "-Dlog4j.configuration=log4j.properties"
)
outputStrategy := Some(StdoutOutput)

resolvers ++= Seq(
  "Central Repository" at "https://repo.maven.apache.org/maven2",
  "typesafe" at "https://repo.typesafe.com/typesafe/repo/"
)

dockerBaseImage := "openjdk:11-jre"
