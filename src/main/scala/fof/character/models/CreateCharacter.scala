package fof.character.models

import cats.effect.IO
import fof.character.models.enums.{Clazz, Race}
import io.circe.generic.auto._
import org.http4s.EntityDecoder
import org.http4s.circe.jsonOf

final case class CreateCharacter(name: String, sex: Boolean, clazz: Clazz, race: Race, bio: String, stats: Stats)

object CreateCharacter {
  implicit val createCharacterDecoder: EntityDecoder[IO, CreateCharacter] = jsonOf[IO, CreateCharacter]
}
