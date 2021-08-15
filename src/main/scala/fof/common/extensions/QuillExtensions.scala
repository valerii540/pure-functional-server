package fof.common.extensions

import cats.effect.IO
import fof.common.database.DatabaseContext.dc
import fof.common.database.DatabaseContext
import doobie.implicits._

object QuillExtensions {

  implicit class ResultExtensions[T](result: dc.Result[T]) {
    def transact: IO[T] = result.transact(DatabaseContext.xa)

    def transactVoid: IO[Unit] = result.transact(DatabaseContext.xa).void
  }
}

