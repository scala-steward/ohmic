package com.nigeleke.resistors.core

import squants.Quantity
import squants.electro._
import squants.experimental.formatter.{DefaultFormatter, Formatter}
import squants.experimental.unitgroups.UnitGroup
import squants.experimental.unitgroups.si.strict.implicits._

import scala.language.implicitConversions

case class Resistor(base: Base, multiplier: Multiplier)

object Resistor {

  def setFor(series: Series) = (for {
    baseValue <- series.values
    multiplier <- Multiplier.multipliers
  } yield Resistor(baseValue, multiplier)).toSet

  lazy val fullSet = Series.all.foldLeft(Set.empty[Resistor])((set, series) => set ++ setFor(series))

  implicit class ResistorOps(r: Resistor) {
    lazy val base = r.base
    lazy val multiplier = r.multiplier

    lazy val value : ElectricalResistance = Ohms(multiplier.fn(base))

    lazy val formattedValue : ElectricalResistance = {
      val unitGroup = implicitly[UnitGroup[ElectricalResistance]]
      val formatter = new DefaultFormatter(unitGroup)
      formatter.inBestUnit(value)
    }

    lazy val colourCodes : Seq[BandColour] = {
      val baseValueString = base.toString
      val bandColours : Seq[BandColour] = baseValueString.map(c => Band.from(c))
      val multiplierColour : BandColour = multiplier
      bandColours :+ multiplierColour
    }
  }

}
