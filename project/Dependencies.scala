import sbt._

object Dependencies {
  private object versions {
    val doobie     = "1.0.0-M5"
    val cats       = "2.6.1"
    val catsEffect = "3.2.8"
    val enumeratum = "1.7.0"
    val pureConfig = "0.17.0"
    val fs2        = "3.1.5"
    val http4s     = "0.23.4"
    val logback    = "1.2.6"
    val circe      = "0.14.1"
    val sqlite     = "3.36.0.2"
    val refined    = "0.9.27"
  }

  val libraries: Seq[ModuleID] = Seq(
    "org.tpolecat"          %% "doobie-core"         % versions.doobie,
    "org.tpolecat"          %% "doobie-postgres"     % versions.doobie,
    "org.tpolecat"          %% "doobie-quill"        % versions.doobie,
    "org.xerial"            % "sqlite-jdbc"          % versions.sqlite,
    "org.typelevel"         %% "cats-core"           % versions.cats,
    "org.typelevel"         %% "cats-effect"         % versions.catsEffect,
    "com.beachape"          %% "enumeratum"          % versions.enumeratum,
    "com.beachape"          %% "enumeratum-circe"    % versions.enumeratum,
    "com.github.pureconfig" %% "pureconfig"          % versions.pureConfig,
    "co.fs2"                %% "fs2-core"            % versions.fs2,
    "org.http4s"            %% "http4s-blaze-server" % versions.http4s,
    "org.http4s"            %% "http4s-blaze-client" % versions.http4s,
    "org.http4s"            %% "http4s-dsl"          % versions.http4s,
    "org.http4s"            %% "http4s-circe"        % versions.http4s,
    "io.circe"              %% "circe-generic"       % versions.circe,
    "io.circe"              %% "circe-literal"       % versions.circe,
    "io.circe"              %% "circe-refined"       % versions.circe,
    "eu.timepit"            %% "refined"             % versions.refined,
    "ch.qos.logback"        % "logback-classic"      % versions.logback
  )
}
