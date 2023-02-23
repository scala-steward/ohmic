package com.nigeleke.ohmic

import app.*

import org.scalatest
import org.scalatest.matchers.should.*
import org.scalatest.wordspec.*

import java.nio.file.*

class ResistorAppSpec extends AnyWordSpec with Matchers:

  "Main app should create file resistor.xlsx as output" in {
    val file = Path.of("resistors.xlsx")
    Files.exists(file) should be(false)
    ResistorApp.main
    Files.exists(file) should be(true)
    Files.delete(file)
  }
