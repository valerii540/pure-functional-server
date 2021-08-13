package fof.user.models

import java.time.Instant
import java.util.UUID

final case class User(id: UUID,
                      nickname: String,
                      email: String,
                      password: String,
                      age: Int,
                      country: String,
                      created: Instant)
