package com.nigeleke.resistors.app

import java.nio.file.Paths

import com.nigeleke.resistors.core._
import com.nigeleke.resistors.document.ResistorDocument

import scala.jdk.CollectionConverters._

object ResistorApp extends App {

  ResistorDocument()
    .withResistors(Resistor.setFor(Series.E24).toSeq.sortBy(_.value))
    .saveAs(Paths.get("resistors.xlsx"))

}
