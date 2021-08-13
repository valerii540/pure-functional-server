package fof.user.models

import enumeratum._
import enumeratum.EnumEntry._
import org.http4s.QueryParamDecoder

sealed trait SortBy extends EnumEntry with Lowercase

object SortBy extends Enum[SortBy] {
  override val values: IndexedSeq[SortBy] = findValues

  case object Age      extends SortBy
  case object Nickname extends SortBy
  case object Country  extends SortBy
  case object Created  extends SortBy

  implicit val queryParamDecoder: QueryParamDecoder[SortBy] = QueryParamDecoder[String] map withName
}
