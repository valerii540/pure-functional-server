package fof.character.models.enums

import enumeratum.EnumEntry._
import enumeratum._

sealed trait Race extends EnumEntry with Lowercase

object Race extends Enum[Race] with CirceEnum[Race] {
  override val values: IndexedSeq[Race] = findValues

  case object Human    extends Race
  case object Ork      extends Race
  case object Elf      extends Race
  case object Dwarf    extends Race
  case object Demon    extends Race
  case object Angel    extends Race
  case object Hedgehog extends Race
}
