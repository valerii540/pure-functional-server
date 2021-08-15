package fof

import cats.effect._
import fof.user.UserController
import org.http4s.blaze.server.BlazeServerBuilder

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
