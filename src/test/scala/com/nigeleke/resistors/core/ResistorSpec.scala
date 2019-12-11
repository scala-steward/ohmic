package com.nigeleke.resistors.core

import org.scalatest.{Matchers, WordSpec}

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
    values should contain allElementsOf(Resistor.baseValues)
    values should contain allElementsOf(Resistor.baseValues.map(_ * 10))
    values should contain allElementsOf(Resistor.baseValues.map(_ * 100))
    values should contain allElementsOf(Resistor.baseValues.map(_ * 1000))
    values should contain allElementsOf(Resistor.baseValues.map(_ * 10000))
    values should contain allElementsOf(Resistor.baseValues.map(_ * 100000))
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

  def resistorWithValue(v: Long) = Resistor.fullSet.filter(_.value == v).head

  "A Resistor will be colour coded for base value, multiplier and tolerance" in {
    for {
      value <- Resistor.baseValues
      multiple <- Resistor.multiples
    } yield {
      val resistor = resistorWithValue((value * multiple.value).toLong)
      val codes = resistor.colourCodes
      import Resistor._
      val expectedCodes = resistor.bands.map(bandToBandColour) :+ multiplierToBandColour(resistor.multiplier)
      codes should contain theSameElementsInOrderAs(expectedCodes)
    }
  }

}
