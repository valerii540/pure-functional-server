package fof.user

import cats.effect.IO
import fof.user.models.{CreateUser, SortBy, UpdateUser, User}

import java.time.Instant
import java.util.UUID

object UserService {
  def addUser(createUser: CreateUser): IO[Unit] = UserDAO.addUser(
    User(
      id = UUID.randomUUID(),
      nickname = createUser.nickname,
      email = createUser.email,
      password = createUser.password,
      age = createUser.age,
      country = createUser.country,
      created = Instant.now(),
      verified = false
    )
  )

  def getUser(id: UUID): IO[User] = UserDAO.getUser(id)

  def getUsers(sortBy: SortBy, desc: Boolean, limit: Int, offset: Int): IO[Seq[User]] =
    UserDAO.getUsers(sortBy, desc, limit, offset)

  def updateUser(id: UUID, newValues: UpdateUser): IO[Unit] = UserDAO.updateUser(id, newValues)
}
