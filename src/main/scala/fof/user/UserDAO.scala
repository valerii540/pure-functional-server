package fof.user

import cats.effect.IO
import doobie.implicits._
import fof.user.models.User
import fof.common.database.CustomDBEncoders._
import fof.common.database.DatabaseContext

object UserDAO {
  import fof.common.database.DatabaseContext.dc._

  def addUser(user: User): IO[Unit] =
    run {
      quote {
        query[User].insert(lift(user))
      }
    }.transact(DatabaseContext.xa).void

  def getUsers: IO[Seq[User]] = run {
    quote {
      query[User]
    }
  }.transact(DatabaseContext.xa)

}
