package com.nigeleke.ohmic
package document

import core.*

import org.apache.poi.xwpf.usermodel.*
import org.scalatest.*
import org.scalatest.matchers.should.*
import org.scalatest.wordspec.*
import java.nio.file._

class ResistorDocumentSpec extends AnyWordSpec with Matchers:

  "The document will be saveable" in {
    val tempFolder = Files.createTempDirectory("comNigelEkeOhmicTest")
    val tempFile   = tempFolder.resolve("saveableTest.xlsx")

    Files.exists(tempFile) should be(false)

    val document = ResistorDocument()
    document.saveAs(tempFile)

    Files.exists(tempFile) should be(true)

    Files.deleteIfExists(tempFile)
    Files.deleteIfExists(tempFolder)
  }

  "The document will create a row for each Resistor" in {
    val tempFolder = Files.createTempDirectory("comNigelEkeOhmicTest")
    val tempFile   = tempFolder.resolve("dataTest.xlsx")

    val document = ResistorDocument()

    val bandColours1 = Seq(BandColour.White, BandColour.Red, BandColour.Orange)
    val bandColours2 = bandColours1 :+ BandColour.Yellow
    val row          = ResistorRow(25, "ohm", Seq(bandColours2, bandColours1))

    document.withRow(row)
    document.saveAs(tempFile)

    Files.exists(tempFile) should be(true)

    Files.deleteIfExists(tempFile)
    Files.deleteIfExists(tempFolder)
  }

  "The document will create a row for many Resistors" in {
    val tempFolder = Files.createTempDirectory("comNigelEkeOhmicTest")
    val tempFile   = tempFolder.resolve("dataTest2.xlsx")

    val document     = ResistorDocument()
    val bandColours1 = Seq(BandColour.White, BandColour.Red, BandColour.Orange)
    val bandColours2 = bandColours1 :+ BandColour.Yellow
    val row          = ResistorRow(25, "ohm", Seq(bandColours2, bandColours1))
    val rows         = Seq(row, row, row)

    document.withRows(rows)
    document.saveAs(tempFile)

    Files.exists(tempFile) should be(true)

    Files.deleteIfExists(tempFile)
    Files.deleteIfExists(tempFolder)
  }
