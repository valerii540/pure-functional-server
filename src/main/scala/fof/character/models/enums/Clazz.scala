package fof.character.models.enums

import enumeratum.EnumEntry._
import enumeratum._

sealed trait Clazz extends EnumEntry with Lowercase

object Clazz extends Enum[Clazz] with CirceEnum[Clazz] {
  override val values: IndexedSeq[Clazz] = findValues

  case object Warrior extends Clazz
  case object Thief   extends Clazz
  case object Mage    extends Clazz
  case object Ranger  extends Clazz
}
