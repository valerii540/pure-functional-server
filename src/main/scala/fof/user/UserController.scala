package fof.user

import cats.effect._
import fof.user.models.{CreateUser, SortBy, UpdateUser}
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.implicits._

object UserController {

  private object sortByParam extends OptionalQueryParamDecoderMatcher[SortBy]("sort-by")
  private object descParam   extends OptionalQueryParamDecoderMatcher[Boolean]("desc")
  private object limitParam  extends QueryParamDecoderMatcher[Int]("limit")
  private object offsetParam extends QueryParamDecoderMatcher[Int]("offset")

  val routes = HttpRoutes
    .of[IO] {
      case req @ POST -> Root / "users" =>
        for {
          createUser <- req.as[CreateUser]
          _          <- UserService.addUser(createUser)
          resp       <- Created(s"User ${createUser.nickname} has been created")
        } yield resp

      case GET -> Root / "users" :? sortByParam(sortBy) +& descParam(desc) +& limitParam(limit) +& offsetParam(offset) =>
        UserService
          .getUsers(sortBy.getOrElse(SortBy.Created), desc.getOrElse(false), limit, offset)
          .flatMap(users => Ok(users.asJson))

      case GET -> Root / "users" / UUIDVar(userId) =>
        UserService
          .getUser(userId)
          .flatMap(user => Ok(user.asJson))

      case req @ PATCH -> Root / "users" / UUIDVar(userId) =>
        for {
          updateUser <- req.as[UpdateUser]
          _          <- IO.unit
          resp       <- NoContent()
        } yield resp
    }
    .orNotFound
}
