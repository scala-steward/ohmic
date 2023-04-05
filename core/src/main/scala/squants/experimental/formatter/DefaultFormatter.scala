package squants.experimental.formatter

import squants.Quantity
import squants.experimental.unitgroups.UnitGroup

class DefaultFormatter[A <: Quantity[A]](unitGroup: UnitGroup[A]):
  def inBestUnit(quantity: Quantity[A]): A =
    val unit =
      unitGroup.sortedUnits
        .takeWhile { u => quantity.to(u).abs >= 1.0 }
        .lastOption
        .getOrElse(unitGroup.sortedUnits.head)
    quantity.in(unit)
