package com.nigeleke.ohmic
package core

import squants.Quantity
import squants.electro.*
import squants.experimental.formatter.DefaultFormatter
import squants.experimental.unitgroups.UnitGroup
import squants.experimental.unitgroups.si.strict.implicits.*

final case class Resistor(base: Base, multiplier: Multiplier)

object Resistor:
  def setFor(series: Seq[Base]) = (for
    base       <- series
    multiplier <- Multiplier.values
  yield Resistor(base, multiplier)).toSet

  lazy val fullSet =
    val emptySet           = Set.empty[Resistor]
    val unionSeriesWithSet = (set: Set[Resistor], series: Seq[Base]) => set ++ setFor(series)
    Series.all.foldLeft(emptySet)(unionSeriesWithSet)

  extension (r: Resistor)
    def value: ElectricalResistance = Ohms(r.multiplier(r.base))

    def formattedValue: ElectricalResistance =
      val unitGroup = summon[UnitGroup[ElectricalResistance]]
      val formatter = DefaultFormatter(unitGroup)
      formatter.inBestUnit(value)

    def colourCodes: Seq[BandColour] =
      val baseValueString  = r.base.toString
      val bandColours      = baseValueString.map(BandColour.from)
      val multiplierColour = r.multiplier.bandColour
      bandColours :+ multiplierColour
