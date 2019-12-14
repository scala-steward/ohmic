package com.nigeleke.resistors.document

import java.io._
import java.nio.file._

import com.nigeleke.resistors.core.{Band, Multiplier, Resistor}
import org.apache.poi.xssf.usermodel._
import squants.Length
import squants.space.Centimeters

case class ResistorDocument(document: XSSFWorkbook)

object ResistorDocument {

  val sheetName = "Resistor Codes"

  def apply() : ResistorDocument = ResistorDocument(new XSSFWorkbook()).withSheet().withHeader()

  implicit class ResistorDocumentOps(d: ResistorDocument) {

    def withSheet() = {
      d.document.createSheet(sheetName)
      d
    }

    def withHeader() = {
      val row = nextRow()
      row.createCell(0).setCellValue("Value")
      row.createCell(1).setCellValue("Colours")
      d
    }

    def nextRow() = {
      val sheet = d.document.getSheet(sheetName)
      val rowCount = sheet.getPhysicalNumberOfRows
      sheet.createRow(rowCount)
    }

    def saveAs(filePath: Path) = {
      val out = new FileOutputStream(filePath.toFile)
      d.document.write(out);
      d
    }

    def withResistor(resistor: Resistor) = {

      val value = resistor.formattedValue
      val colours = resistor.colourCodes.map(cc => s"$cc").mkString(" - ")

      val row = nextRow()
      row.createCell(0).setCellValue(value)
      row.createCell(1).setCellValue(colours)

      d
    }

    def withResistors(resistors: Seq[Resistor]) = resistors.foldLeft(d)((d0, r) => d0.withResistor(r))

  }

  private val maxBandNameLength = (Band.values().map(_.name().length) ++ Multiplier.values.map(_.name().length)).max

}
