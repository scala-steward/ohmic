package com.nigeleke.resistors.document

import org.apache.poi.xwpf.usermodel._
import org.scalatest.{Matchers, WordSpec}
import java.nio.file._

import com.nigeleke.resistors.core._

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

    val bandColours1 = Seq(BandColour.White, BandColour.Red, BandColour.Orange)
    val bandColours2 = bandColours1 :+ BandColour.Yellow
    val row = ResistorRow(25, "ohm", Seq(bandColours2, bandColours1))

    document.withRow(row)
    document.saveAs(tempFile)

    Files.exists(tempFile) should be(true)

    Files.deleteIfExists(tempFile)
    Files.deleteIfExists(tempFolder)
  }

  "The document will create a row for many Resistors" in {
    val tempFolder = Files.createTempDirectory("comNigelEkeResistorsTest")
    val tempFile = tempFolder.resolve("dataTest2.xlsx")

    val document = ResistorDocument()
    val bandColours1 = Seq(BandColour.White, BandColour.Red, BandColour.Orange)
    val bandColours2 = bandColours1 :+ BandColour.Yellow
    val row = ResistorRow(25, "ohm", Seq(bandColours2, bandColours1))
    val rows = Seq(row, row, row)

    document.withRows(rows)
    document.saveAs(tempFile)

    Files.exists(tempFile) should be(true)

    Files.deleteIfExists(tempFile)
    Files.deleteIfExists(tempFolder)
  }

}
