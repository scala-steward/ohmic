package com.nigeleke.resistors.core

import scala.language.implicitConversions

sealed trait Multiplier { def fn: (Base) => Double }

case  object Multiplier {
  case object byE_2 extends Multiplier { val fn = _ / 100.0 }
  case object byE_1 extends Multiplier { val fn = _ / 10.0 }
  case object byE0 extends Multiplier { val fn = _.toDouble }
  case object byE1 extends Multiplier { val fn = _ * 10.0 }
  case object byE2 extends Multiplier { val fn = _ * 100.0 }
  case object byE3 extends Multiplier { val fn = _ * 1000.0 }
  case object byE4 extends Multiplier { val fn = _ * 10000.0 }
  case object byE5 extends Multiplier { val fn = _ * 100000.0 }
  case object byE6 extends Multiplier { val fn = _ * 1000000.0 }
  case object byE7 extends Multiplier { val fn = _ * 10000000.0 }
  case object byE8 extends Multiplier { val fn = _ * 100000000.0 }
  case object byE9 extends Multiplier { val fn = _ * 1000000000.0 }

  lazy val multipliers = Seq(byE_2, byE_1, byE0, byE1, byE2, byE3, byE4, byE5, byE6, byE7, byE8, byE9)

  implicit def multiplierToBandColour(m: Multiplier) : BandColour = m match {
    case `byE_2` => BandColour.Silver
    case `byE_1` => BandColour.Gold
    case `byE0` => BandColour.Black
    case `byE1` => BandColour.Brown
    case `byE2` => BandColour.Red
    case `byE3` => BandColour.Orange
    case `byE4` => BandColour.Yellow
    case `byE5` => BandColour.Green
    case `byE6` => BandColour.Blue
    case `byE7` => BandColour.Violet
    case `byE8` => BandColour.Grey
    case `byE9` => BandColour.White
  }

}
