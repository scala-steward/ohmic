package com.nigeleke.resistors.document

import java.io._
import java.nio.file._

import com.nigeleke.resistors.core.{Band, Multiplier, Resistor}
import org.apache.poi.xssf.usermodel._
import squants.Length
import squants.space.Centimeters

import scala.language.implicitConversions

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
      row.createCell(1).setCellValue("Unit")
      row.createCell(2).setCellValue("Colours")
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

    def withRow(resistorRow: ResistorRow) = {

      val row = nextRow()
      row.createCell(0).setCellValue(resistorRow.baseValue)
      row.createCell(1).setCellValue(resistorRow.symbol)

      val colours = resistorRow.colours
        .filter(_.size != 0)
        .sortBy(_.size)
        .map(_.mkString(" - "))
        .mkString("\n")

      row.createCell(2).setCellValue(colours)

      d
    }

    def withRows(rows: Seq[ResistorRow]) = rows.foldLeft(d)((d0, r) => d0.withRow(r))

  }
}
