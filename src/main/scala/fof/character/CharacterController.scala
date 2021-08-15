package fof.character

import cats.data.Kleisli
import cats.effect._
import fof.character.models.CreateCharacter
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.{HttpRoutes, Request, Response}

object CharacterController {

  val routes: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes
    .of[IO] {
      case req @ POST -> Root / "character" =>
        req
          .as[CreateCharacter]
          .flatMap(createChar => ???) *> Created()
    }
    .orNotFound
}
