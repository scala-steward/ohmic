package com.nigeleke.resistors.core

import squants.electro._
import squants.experimental.formatter.DefaultFormatter
import squants.experimental.unitgroups.UnitGroup
import squants.experimental.unitgroups.si.strict.implicits._

import scala.jdk.CollectionConverters._
import scala.language.implicitConversions

case class Resistor(baseValue: Int, multiplier: Multiplier)

object Resistor {

  lazy val multiples = Multiplier.values()

  def setFor(series: Series) = (for {
    baseValue <- series.baseValues.asScala
    mulitple <- multiples
  } yield Resistor(baseValue, mulitple)).toSet

  lazy val fullSet = Series.values().foldLeft(Set.empty[Resistor])((set, series) => set ++ setFor(series))

  implicit class ResistorOps(r: Resistor) {
    lazy val baseValue : Int = r.baseValue
    lazy val multiple : Double = r.multiplier.value

    lazy val value : ElectricalResistance = Ohms(r.baseValue * multiple)

    lazy val formattedValue : String = {
      val unitGroup = implicitly[UnitGroup[ElectricalResistance]]
      val formatter = new DefaultFormatter(unitGroup)
      formatter.inBestUnit(value).toString()
    }

    lazy val colourCodes : Seq[BandColour] = {
      val baseValueString = baseValue.toString
      baseValueString.map(c => Band.from(c)).map(bandToBandColour) :+ multiplierToBandColour(r.multiplier)
    }
  }

  implicit def bandToBandColour(band: Band) : BandColour = BandColour.valueOf(band.name())
  implicit def multiplierToBandColour(multiplier: Multiplier) : BandColour = BandColour.valueOf(multiplier.name())
  implicit def toleranceToBandColour(tolerance: Tolerance) : BandColour = BandColour.valueOf(tolerance.name())

}
