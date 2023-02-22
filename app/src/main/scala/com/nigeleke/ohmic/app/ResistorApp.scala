package com.nigeleke.ohmic
package app

import core.*
import document.*

import java.nio.file.Paths

import scala.jdk.CollectionConverters._

object ResistorApp extends App:

  private val series = Resistor.setFor(Series.e24)

  val series5BandMap = (Resistor.fullSet
    .filter(series.contains)
    .filter(_.colourCodes.size == 4)
    .map(r => (r.value, r)))
    .toMap

  val rows = for
    r1 <- series.toList.sortBy(_.value)
    optR2 = series5BandMap.get(r1.value)
  yield
    val v = r1.formattedValue
    val colourCodes =
      Seq(r1.colourCodes) :+ (optR2.map(_.colourCodes).getOrElse(Seq.empty[BandColour]))
    ResistorRow(v.value, v.unit.symbol, colourCodes)

  ResistorDocument()
    .withRows(rows.toSeq)
    .saveAs(Paths.get("resistors.xlsx"))
