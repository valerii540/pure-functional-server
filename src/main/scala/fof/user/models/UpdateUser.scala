package fof.user.models

import cats.effect.IO
import org.http4s.EntityDecoder
import org.http4s.circe.jsonOf
import io.circe.generic.auto._

final case class UpdateUser(password: Option[String], age: Option[Int], country: Option[String])

object UpdateUser {
  implicit val updateUserDecoder: EntityDecoder[IO, UpdateUser] = jsonOf[IO, UpdateUser]
}
