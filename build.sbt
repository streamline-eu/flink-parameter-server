name := "flink-ps"

version := "0.1.0"

scalaVersion := "2.11.7"

lazy val flinkVersion = "1.4.0"
lazy val breezeVersion = "0.13"
resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")

lazy val commonDependencies = Seq(
  "org.scalatest" % "scalatest_2.11" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "org.slf4j" % "slf4j-api" % "1.7.22",
  "com.typesafe" % "config" % "1.3.1",
  "org.foundweekends" % "bintray" % "0.5.4",
  "com.eed3si9n" % "sbt-assembly" % "0.14.6"
)

lazy val json4s = Seq(
  "org.json4s" %% "json4s-native" % "3.6.0-M1",
  "org.json4s" %% "json4s-jackson" % "3.6.0-M1"
)

lazy val flinkDependencies = Seq(
  "org.apache.flink" %% "flink-scala" % flinkVersion,
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion,
  "org.apache.flink" %% "flink-streaming-java" % flinkVersion
)

lazy val hashDependencies = Seq(
  "com.roundeights" %% "hasher" % "1.2.0",
  "net.openhft" % "zero-allocation-hashing" % "0.8"
)

lazy val breezeDependencies = Seq(
  "org.scalanlp" %% "breeze" % breezeVersion,
  // Native libraries are not included by default. add this if you want them (as of 0.7)
  // Native libraries greatly improve performance, but increase jar sizes.
  // It also packages various blas implementations, which have licenses that may or may not
  // be compatible with the Apache License. No GPL code, as best I know.
  "org.scalanlp" %% "breeze-natives" % breezeVersion
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= commonDependencies,
    libraryDependencies ++= flinkDependencies.map(_ % "provided"),
//    libraryDependencies ++= flinkDependencies.map(_ % "compile"),
    libraryDependencies ++= breezeDependencies.map(_ % "compile"),
    libraryDependencies ++= hashDependencies.map(_ % "compile")
)

lazy val commonSettings = Seq(
  organization := "hu.sztaki.ilab",
  version := "0.1.0",
  scalaVersion := "2.11.7",
  test in assembly := {},
  publishArtifact in Test := false,
  bintrayOrganization := Some("streamline"),
  bintrayRepository := "sbt",
  bintrayPackage := "flink-ps"
)
