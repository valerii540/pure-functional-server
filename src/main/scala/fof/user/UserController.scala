package fof.user

import cats.effect._
import fof.user.models.CreateUser
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.implicits._

object UserController {
  val routes = HttpRoutes
    .of[IO] {
      case req @ POST -> Root / "users" =>
        for {
          createUser <- req.as[CreateUser]
          _          <- UserService.saveUser(createUser)
          resp       <- Ok(s"User ${createUser.nickname} created")
        } yield resp

      case GET -> Root / "users" =>
        UserService.getUsers
          .flatMap(users => Ok(users.asJson))
    }
    .orNotFound
}
