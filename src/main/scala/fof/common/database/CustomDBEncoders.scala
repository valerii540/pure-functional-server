package fof.common.database

import io.getquill.MappedEncoding

import java.time.Instant

object CustomDBEncoders {
  implicit val encodeInstant: MappedEncoding[Instant, String] = MappedEncoding(_.toString)
  implicit val decodeInstant: MappedEncoding[String, Instant] = MappedEncoding(Instant.parse)
}
