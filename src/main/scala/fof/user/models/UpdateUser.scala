package fof.user.models

import cats.effect.IO
import io.circe.generic.auto._
import org.http4s.EntityDecoder
import org.http4s.circe.jsonOf

final case class UpdateUser(password: Option[String],
                            age: Option[Int],
                            country: Option[String],
                            verified: Boolean)

object UpdateUser {
  implicit val updateUserDecoder: EntityDecoder[IO, UpdateUser] = jsonOf[IO, UpdateUser]
}
