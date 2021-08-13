package fof.user

import cats.effect.IO
import doobie.implicits._
import fof.user.models.{SortBy, UpdateUser, User}
import fof.common.database.CustomDBEncoders._
import fof.common.database.DatabaseContext
import io.getquill.Ord

import java.util.UUID

object UserDAO {
  import fof.common.database.DatabaseContext.dc._

  def addUser(user: User): IO[Unit] =
    run {
      quote {
        query[User].insert(lift(user))
      }
    }.transact(DatabaseContext.xa).void

  def getUser(id: UUID): IO[User] =
    run {
      quote {
        query[User].filter(_.id == lift(id))
      }
    }.transact(DatabaseContext.xa).map(_.head)

  def getUsers(sortBy: SortBy, desc: Boolean, limit: Int, offset: Int): IO[Seq[User]] = {
    val sorted = (sortBy, desc) match {
      case (SortBy.Age, true)       => quote(query[User].sortBy(_.age)(Ord.desc))
      case (SortBy.Age, false)      => quote(query[User].sortBy(_.age)(Ord.asc))
      case (SortBy.Nickname, true)  => quote(query[User].sortBy(_.nickname)(Ord.desc))
      case (SortBy.Nickname, false) => quote(query[User].sortBy(_.nickname)(Ord.asc))
      case (SortBy.Country, true)   => quote(query[User].sortBy(_.country)(Ord.desc))
      case (SortBy.Country, false)  => quote(query[User].sortBy(_.country)(Ord.asc))
      case (SortBy.Created, true)   => quote(query[User].sortBy(_.created)(Ord.desc))
      case (SortBy.Created, false)  => quote(query[User].sortBy(_.created)(Ord.asc))
    }

    run {
      quote {
        sorted
          .drop(lift(offset))
          .take(lift(limit))
      }
    }.transact(DatabaseContext.xa)
  }

  def updateUser(id: UUID, newValues: UpdateUser): IO[Unit] = {
    quote {
      query[User].filter(_.id == lift(id))
    }

    ???
  }

}
