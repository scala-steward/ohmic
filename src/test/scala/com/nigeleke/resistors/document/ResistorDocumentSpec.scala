package com.nigeleke.resistors.document

import org.apache.poi.xwpf.usermodel._
import org.scalatest.{Matchers, WordSpec}
import java.nio.file._

import com.nigeleke.resistors.core.{Band, Multiplier, Resistor}

class ResistorDocumentSpec extends WordSpec with Matchers {

  "The document will be saveable" in {
    val tempFolder = Files.createTempDirectory("comNigelEkeResistorsTest")
    val tempFile = tempFolder.resolve("saveableTest.xlsx")

    Files.exists(tempFile) should be(false)

    val document = ResistorDocument()
    document.saveAs(tempFile)

    Files.exists(tempFile) should be(true)

    Files.deleteIfExists(tempFile)
    Files.deleteIfExists(tempFolder)
  }

  "The document will create a row for each Resistor" in {
    val tempFolder = Files.createTempDirectory("comNigelEkeResistorsTest")
    val tempFile = tempFolder.resolve("dataTest.xlsx")

    val document = ResistorDocument()
    val resistor = Resistor(Seq(Band.Red, Band.Green), Multiplier.Blue)
    document.withResistor(resistor)
    document.saveAs(tempFile)

    Files.exists(tempFile) should be(true)

    Files.deleteIfExists(tempFile)
    Files.deleteIfExists(tempFolder)
  }

  "The document will create a row for many Resistors" in {
    val tempFolder = Files.createTempDirectory("comNigelEkeResistorsTest")
    val tempFile = tempFolder.resolve("dataTest2.xlsx")

    val document = ResistorDocument()
    val resistors = Seq(
      Resistor(Seq(Band.Red, Band.Green), Multiplier.Blue),
      Resistor(Seq(Band.Brown, Band.Grey, Band.Orange), Multiplier.Violet))
    document.withResistors(resistors)
    document.saveAs(tempFile)

    Files.exists(tempFile) should be(true)

    Files.deleteIfExists(tempFile)
    Files.deleteIfExists(tempFolder)
  }

}
