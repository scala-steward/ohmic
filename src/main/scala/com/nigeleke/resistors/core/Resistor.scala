package com.nigeleke.resistors.core

import scala.language.implicitConversions

case class Resistor(bands: Seq[Band], multiplier: Multiplier, tolerance: Tolerance)

object Resistor {

  lazy val e24BaseValues = Seq(
    10, 11, 12, 13, 15, 16, 18, 20, 22, 24, 27, 30,
    33, 36, 39, 43, 47, 51, 56, 62, 68, 75, 82, 91)

  lazy val multiples = Multiplier.values()

  lazy val e24Set = genSet(e24BaseValues, Tolerance.Gold)

  private def genSet(baseValues: Seq[Int], tolerance: Tolerance) = (for {
    baseValue <- baseValues
    multiple <- multiples
  } yield {
    val baseValueString = baseValue.toString
    val bands = baseValueString.map(Band.from(_))
    Resistor(bands, multiple, tolerance)
  }).toSet

  implicit class ResistorOps(r: Resistor) {
    lazy val significantBandsValue : Int = r.bands.foldLeft(0)((v, b) => v * 10 + b.value)
    lazy val multiple : Double = r.multiplier.value

    lazy val value : Long = (significantBandsValue * multiple).toLong

    lazy val code = {
      val lenValueString = value.toString.length

      val (divisor, divisorString) = ((lenValueString - 1) / 3) match {
        case 0 => (1, "R")
        case 1 => (1000, "K")
        case _ => (1000000, "M")
      }

      val mod = r.value / divisor
      val modString = mod.toString

      def stripTrailingZeroes(s: String) : String = s.reverse.dropWhile(_ == '0').reverse

      val remainder = r.value - (mod * divisor)
      val remainderString = stripTrailingZeroes(remainder.toString)

      s"$modString$divisorString$remainderString"
    }

    lazy val colourCodes : Seq[BandColour] = r.bands.map(bandToBandColour) :+
      multiplierToBandColour(r.multiplier) :+
      toleranceToBandColour(r.tolerance)
  }

  implicit def bandToBandColour(band: Band) : BandColour = BandColour.valueOf(band.name())
  implicit def multiplierToBandColour(multiplier: Multiplier) : BandColour = BandColour.valueOf(multiplier.name())
  implicit def toleranceToBandColour(tolerance: Tolerance) : BandColour = BandColour.valueOf(tolerance.name())

}
