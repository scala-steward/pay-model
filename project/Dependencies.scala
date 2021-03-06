import sbt._
// This object is only used when running sbt from the pay-model directory, a project like pay or mathbot will use
// the Dependencies object defined in its ./project directory instead

//noinspection SpellCheckingInspection
object Dependencies {
  lazy val playJsonV = "2.7.4"
  lazy val scalaTestV = "3.2.8"
  lazy val akkaVersion = "2.5.32"
  lazy val sttpVersion = "2.2.9"
  lazy val alpakkaSocketV = "2.0.2"
  lazy val macwireVersion = "2.3.7"
  lazy val sttpModelV = "1.3.4"
  lazy val bitcoinjV = "0.15.10"
  lazy val mockitoV = "3.2.8.0"

  lazy val sttp = Seq(
    "com.softwaremill.sttp.client" %% "core" % sttpVersion,
    "com.softwaremill.sttp.client" %% "akka-http-backend" % sttpVersion,
    "com.softwaremill.sttp.client" %% "play-json" % sttpVersion
  )
  val requests = "com.lihaoyi" %% "requests" % "0.6.7"
  val bitcoinLib = "fr.acinq" %% "bitcoin-lib" % "0.19"
  val scodec = "org.scodec" %% "scodec-core" % "1.11.7"

  lazy val okhttp = "com.softwaremill.sttp.client" %% "okhttp-backend" % sttpVersion
  lazy val akkaStreamTestkit = "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % "it,test"
  lazy val akkaTestkit =
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "it,test"
  lazy val akkaStream = "com.typesafe.akka" %% "akka-stream" % akkaVersion
  lazy val unixSocket = "com.lightbend.akka" %% "akka-stream-alpakka-unix-domain-socket" % alpakkaSocketV
  lazy val sttpModel = "com.softwaremill.sttp.model" %% "core" % sttpModelV
  lazy val bitcoinj = "org.bitcoinj" % "bitcoinj-core" % bitcoinjV

  lazy val scalaTest = "org.scalatest" %% "scalatest" % scalaTestV % "it,test"
  lazy val scalactic = "org.scalactic" %% "scalactic" % scalaTestV % "it,test"

  lazy val playJson = "com.typesafe.play" %% "play-json" % playJsonV
  lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  lazy val macwire = Seq(
    "com.softwaremill.macwire" %% "macros" % macwireVersion % "provided",
    "com.softwaremill.macwire" %% "macrosakka" % macwireVersion % "provided",
    "com.softwaremill.macwire" %% "util" % macwireVersion,
    "com.softwaremill.macwire" %% "proxy" % macwireVersion
  )
  lazy val mockito = "org.scalatestplus" %% "mockito-3-4" % mockitoV % "it,test"

}
