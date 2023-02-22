/*
 * Copyright (c) 2023, Nigel Eke
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
