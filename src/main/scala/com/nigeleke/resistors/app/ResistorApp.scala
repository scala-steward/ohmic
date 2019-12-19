package com.nigeleke.resistors.app

import java.nio.file.Paths

import com.nigeleke.resistors.core.{Resistor, Series}
import com.nigeleke.resistors.document.ResistorDocument

object ResistorApp extends App {

  ResistorDocument()
    .withResistors(Resistor.setFor(Series.E24).toSeq.sortBy(_.value))
    .saveAs(Paths.get("resistors.xlsx"))

}
