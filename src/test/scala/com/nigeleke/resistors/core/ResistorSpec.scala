package com.nigeleke.resistors.core

import org.scalatest.{Matchers, WordSpec}
import squants.electro._
import scala.language.implicitConversions

class ResistorSpec extends WordSpec with Matchers {

  "Resistors series are defined by base values" in {
    Resistor.baseValues should contain allOf( // e24 series
      10, 11, 12, 13, 15, 16, 18, 20, 22, 24, 27, 30,
      33, 36, 39, 43, 47, 51, 56, 62, 68, 75, 82, 91)
    Resistor.baseValues.size should be(216)   // fullSeries
  }

  "Resistor multipliers will include unit to mega" in {
    val multiples = Multiplier.values()
    Resistor.multiples should contain theSameElementsAs(multiples)
  }

  "A set of Resistors will be the base values with all multipliers" in {
    val resistors = Resistor.fullSet
    resistors.size should be (Resistor.baseValues.size * Resistor.multiples.size)

    val values = resistors.map(_.value)
    values should contain allElementsOf(Resistor.baseValues.map(Ohms(_)))
    values should contain allElementsOf(Resistor.baseValues.map(Ohms(_) * 10))
    values should contain allElementsOf(Resistor.baseValues.map(Ohms(_) * 100))
    values should contain allElementsOf(Resistor.baseValues.map(Ohms(_) * 1000))
    values should contain allElementsOf(Resistor.baseValues.map(Ohms(_) * 10000))
    values should contain allElementsOf(Resistor.baseValues.map(Ohms(_) * 100000))
  }

  "A Resistor's formatted value will include unit, kilo & mega multipliers" in {
    resistorWithValue(Ohms(22)).formattedValue should be("22.0 Ω")
    resistorWithValue(Ohms(220)).formattedValue should be("220.0 Ω")
    resistorWithValue(Ohms(2200)).formattedValue should be("2.2 kΩ")
    resistorWithValue(Ohms(22000)).formattedValue should be("22.0 kΩ")
    resistorWithValue(Ohms(220000)).formattedValue should be("220.0 kΩ")
    resistorWithValue(Ohms(2200000)).formattedValue should be("2.2 MΩ")
    resistorWithValue(Ohms(22000000)).formattedValue should be("22.0 MΩ")
    resistorWithValue(Ohms(220000000)).formattedValue should be("220.0 MΩ")
  }

  def resistorWithValue(v: ElectricalResistance) = Resistor.fullSet.filter(_.value == v).head

  "A Resistor will be colour coded for base value, multiplier and tolerance" in {
    for {
      value <- Resistor.baseValues
      multiple <- Resistor.multiples
    } yield {
      val resistor = resistorWithValue(Ohms(value * multiple.value))
      val codes = resistor.colourCodes
      import Resistor._
      val expectedCodes = resistor.bands.map(bandToBandColour) :+ multiplierToBandColour(resistor.multiplier)
      codes should contain theSameElementsInOrderAs(expectedCodes)
    }
  }

}
