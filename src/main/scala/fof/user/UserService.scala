package fof.user

import cats.effect.IO
import fof.user.models.{CreateUser, User}

import java.time.Instant
import java.util.UUID

object UserService {
  def saveUser(createUser: CreateUser): IO[Unit] = UserDAO.addUser(
    User(
      id = UUID.randomUUID(),
      nickname = createUser.nickname,
      email = createUser.email,
      password = createUser.password,
      age = createUser.age,
      country = createUser.country,
      created = Instant.now()
    )
  )

  def getUsers: IO[Seq[User]] = UserDAO.getUsers
}
