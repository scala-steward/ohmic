package com.nigeleke.resistors.app

import java.nio.file.Paths

import com.nigeleke.resistors.core._
import com.nigeleke.resistors.document.{ResistorDocument, ResistorRow}

import scala.jdk.CollectionConverters._

object ResistorApp extends App {

  val series = Resistor.setFor(Series.E24)
  val seriesValues = series.map(_.value)

  val series5BandMap = (Resistor.fullSet
    .filter(r => seriesValues.contains(r.value))
    .filter(r => r.colourCodes.size == 4)
    .map(r => (r.value, r))).toMap

  val rows = for {
    r1 <- series.toList.sortBy(_.value)
    optR2 = series5BandMap.get(r1.value)
  } yield {
    val v = r1.formattedValue
    val colourCodes = Seq(r1.colourCodes) :+ (optR2.map(_.colourCodes).getOrElse(Seq.empty[BandColour]))
    ResistorRow(v.value, v.unit.symbol, colourCodes)
  }

  ResistorDocument()
    .withRows(rows.toSeq)
    .saveAs(Paths.get("resistors.xlsx"))

}
