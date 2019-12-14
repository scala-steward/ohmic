package com.nigeleke.resistors.app

import java.nio.file.Paths

import com.nigeleke.resistors.core.Resistor
import com.nigeleke.resistors.document.ResistorDocument

object ResistorApp extends App {

  ResistorDocument()
    .withResistors(Resistor.fullSet.filter(_.bands.size == 2).toSeq.sortBy(_.value))
    .saveAs(Paths.get("resistors.xlsx"))

}
