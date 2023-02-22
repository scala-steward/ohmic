package com.nigeleke.ohmic
package document

import core.*

import java.io.*
import java.nio.file.*

import org.apache.poi.xssf.usermodel.*
import squants.Length
import squants.space.Centimeters

final case class ResistorDocument(document: XSSFWorkbook)

object ResistorDocument:

  val sheetName = "Resistor Codes"

  def apply(): ResistorDocument = ResistorDocument(XSSFWorkbook()).withSheet().withHeader()

  extension (d: ResistorDocument)
    def withSheet() =
      d.document.createSheet(sheetName)
      d

    def withHeader(): ResistorDocument =
      val row = nextRow()
      row.createCell(0).setCellValue("Value")
      row.createCell(1).setCellValue("Unit")
      row.createCell(2).setCellValue("Colours")
      d

    def nextRow() =
      val sheet    = d.document.getSheet(sheetName)
      val rowCount = sheet.getPhysicalNumberOfRows
      sheet.createRow(rowCount)

    def saveAs(filePath: Path) =
      val out = FileOutputStream(filePath.toFile)
      d.document.write(out)
      out.close()
      d

    def withRow(resistorRow: ResistorRow): ResistorDocument =
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

    def withRows(rows: Seq[ResistorRow]) = rows.foldLeft(d)((d0, r) => d0.withRow(r))
