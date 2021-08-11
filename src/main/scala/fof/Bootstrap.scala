package fof

import cats.effect._
import fof.user.UserController
import org.http4s.HttpRoutes
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.io._
import org.http4s.implicits._

object Bootstrap extends IOApp {

  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO](runtime.compute)
      .bindHttp(8080, "localhost")
      .withHttpApp(UserController.routes)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
