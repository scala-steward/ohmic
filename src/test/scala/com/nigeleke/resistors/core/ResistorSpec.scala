package com.nigeleke.resistors.core

import org.scalatest.{Matchers, WordSpec}

import scala.language.implicitConversions

class ResistorSpec extends WordSpec with Matchers {

  "Resistors series are defined by base values" in {
    Resistor.e24BaseValues should contain allOf(
      10, 11, 12, 13, 15, 16, 18, 20, 22, 24, 27, 30,
      33, 36, 39, 43, 47, 51, 56, 62, 68, 75, 82, 91)
    Resistor.e24BaseValues.size should be(24)
  }

  "Resistor multipliers will include unit to mega" in {
    val multiples = Multiplier.values()
    Resistor.multiples should contain theSameElementsAs(multiples)
  }

  "A set of Resistors will be the base values with all multipliers" in {
    val resistors = Resistor.e24Set
    resistors.size should be (Resistor.e24BaseValues.size * Resistor.multiples.size)

    val values = resistors.map(_.value)
    values should contain allElementsOf(Resistor.e24BaseValues)
    values should contain allElementsOf(Resistor.e24BaseValues.map(_ * 10))
    values should contain allElementsOf(Resistor.e24BaseValues.map(_ * 100))
    values should contain allElementsOf(Resistor.e24BaseValues.map(_ * 1000))
    values should contain allElementsOf(Resistor.e24BaseValues.map(_ * 10000))
    values should contain allElementsOf(Resistor.e24BaseValues.map(_ * 100000))
  }

  "The e24 set of Resistors are 5% tolerence" in {
    val tolerances = Resistor.e24Set.map(_.tolerance)
    tolerances.size should be(1)
    tolerances.head should be(Tolerance.Gold)
  }

  "A Resistor's code will include R, K & M multipliers" in {
    resistorWithValue(22).code should be("22R")
    resistorWithValue(220).code should be("220R")
    resistorWithValue(2200).code should be("2K2")
    resistorWithValue(22000).code should be("22K")
    resistorWithValue(220000).code should be("220K")
    resistorWithValue(2200000).code should be("2M2")
    resistorWithValue(22000000).code should be("22M")
    resistorWithValue(220000000).code should be("220M")
  }

  def resistorWithValue(v: Long) = Resistor.e24Set.filter(_.value == v).head

  "A Resistor will be colour coded for base value, multiplier and tolerance" in {
    for {
      value <- Resistor.e24BaseValues
      multiple <- Resistor.multiples
    } yield {
      val resistor = resistorWithValue((value * multiple.value).toLong)
      val codes = resistor.colourCodes
      import Resistor._
      val expectedCodes = resistor.bands.map(bandToBandColour) :+
        multiplierToBandColour(resistor.multiplier) :+
        toleranceToBandColour(resistor.tolerance)
      codes should contain theSameElementsInOrderAs(expectedCodes)
    }
  }

}
