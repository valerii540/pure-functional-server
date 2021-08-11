package fof.user.models

import cats.effect.IO
import org.http4s.EntityEncoder
import org.http4s.circe.jsonOf

import java.time.Instant
import java.util.UUID

final case class User(id: UUID, nickname: String, email: String, password: String, age: Int, country: String, created: Instant)

//object User {
//  implicit val userEncoder: EntityEncoder[IO, User] = jsonOf[IO, User]
//}
