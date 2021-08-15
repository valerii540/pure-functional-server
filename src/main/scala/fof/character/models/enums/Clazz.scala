package fof.character.models.enums

import enumeratum.EnumEntry._
import enumeratum.{EnumEntry, _}

trait Clazz extends EnumEntry with Lowercase

object Clazz extends Enum[Clazz] {
  override val values: IndexedSeq[Clazz] = findValues

  case object Warrior extends Clazz
  case object Thief   extends Clazz
  case object Mage    extends Clazz
  case object Ranger  extends Clazz
}
