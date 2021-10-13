package fof.character.models

import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import fof.character.models.Stats.Value
import eu.timepit.refined.numeric.Interval

final case class Stats(
    strength: Value,
    dexterity: Value,
    constitution: Value,
    intelligence: Value,
    wisdom: Value,
    charisma: Value
)

object Stats {
  type Value = Int Refined Interval.Closed[1, 10]

  def empty: Stats = Stats(1, 1, 1, 1, 1, 1)
}
