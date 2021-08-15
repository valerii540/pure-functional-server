package fof.user

import cats.data.Kleisli
import cats.effect._
import fof.user.models.{CreateUser, SortBy, UpdateUser}
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.{HttpRoutes, Request, Response}

object UserController {

  //TODO: should we separate common parameters?
  private object sortByParam extends OptionalQueryParamDecoderMatcher[SortBy]("sort-by")
  private object descParam   extends OptionalQueryParamDecoderMatcher[Boolean]("desc")
  private object limitParam  extends QueryParamDecoderMatcher[Int]("limit")
  private object offsetParam extends QueryParamDecoderMatcher[Int]("offset")

  val routes: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes
    .of[IO] {
      case req @ POST -> Root / "users" =>
        req
          .as[CreateUser]
          .flatMap(createUser => UserService.addUser(createUser)) *> Created()

      case GET -> Root / "users" :? sortByParam(sortBy) +& descParam(desc) +& limitParam(limit) +& offsetParam(offset) =>
        UserService
          .getUsers(sortBy.getOrElse(SortBy.Created), desc.getOrElse(false), limit, offset)
          .flatMap(users => Ok(users.asJson))

      case GET -> Root / "users" / UUIDVar(id) =>
        UserService
          .getUser(id)
          .flatMap(user => Ok(user.asJson))

      case req @ PATCH -> Root / "users" / UUIDVar(id) =>
        req
          .as[UpdateUser]
          .flatMap(updateUser => UserService.updateUser(id, updateUser)) *> NoContent()
    }
    .orNotFound
}
