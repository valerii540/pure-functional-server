package fof.user.models

import cats.effect.IO
import org.http4s.circe.jsonOf
import io.circe.generic.auto._
import org.http4s.EntityDecoder

final case class CreateUser(nickname: String, email: String, password: String, age: Int, country: String)

object CreateUser {
  implicit val createUserDecoder: EntityDecoder[IO, CreateUser] = jsonOf[IO, CreateUser]
}
