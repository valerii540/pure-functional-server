package fof.common.database

import cats.effect.IO
import doobie.quill.DoobieContext
import doobie.util.transactor.Transactor
import doobie.util.transactor.Transactor.Aux
import io.getquill.{idiom => _, _}

object DatabaseContext {
  val dc = new DoobieContext.SQLite(SnakeCase)

  val xa: Aux[IO, Unit] = Transactor.fromDriverManager[IO](
    driver = "org.sqlite.JDBC",
    url = "jdbc:sqlite:sample.db",
    user = "pure",
    pass = "pure"
  )
}
