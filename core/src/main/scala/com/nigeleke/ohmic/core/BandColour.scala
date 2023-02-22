package com.nigeleke.ohmic
package core

enum BandColour:
  case Black, Brown, Red, Orange, Yellow, Green, Blue, Violet, Grey, White, Gold, Silver

object BandColour:
  // format: off
  def from(c: Char): BandColour =
    Map(
      ('0', Black), ('1', Brown), ('2', Red), ('3', Orange), ('4', Yellow),
      ('5', Green), ('6', Blue), ('7', Violet), ('8', Grey), ('9', White)
    ).get(c).get
  // format: oon
