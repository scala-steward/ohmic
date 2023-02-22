package com.nigeleke.ohmic
package core

enum Multiplier(fn: Int => Double, val bandColour: BandColour):
  case ByE_2 extends Multiplier(_ / 100.0, BandColour.Silver)
  case ByE_1 extends Multiplier(_ / 10.0, BandColour.Gold)
  case ByE0  extends Multiplier(_.toDouble, BandColour.Black)
  case ByE1  extends Multiplier(_ * 10.0, BandColour.Brown)
  case ByE2  extends Multiplier(_ * 100.0, BandColour.Red)
  case ByE3  extends Multiplier(_ * 1000.0, BandColour.Orange)
  case ByE4  extends Multiplier(_ * 10000.0, BandColour.Yellow)
  case ByE5  extends Multiplier(_ * 100000.0, BandColour.Green)
  case ByE6  extends Multiplier(_ * 1000000.0, BandColour.Blue)
  case ByE7  extends Multiplier(_ * 10000000.0, BandColour.Violet)
  case ByE8  extends Multiplier(_ * 100000000.0, BandColour.Grey)
  case ByE9  extends Multiplier(_ * 1000000000.0, BandColour.White)

  def apply(base: Base): Double = fn(base)
