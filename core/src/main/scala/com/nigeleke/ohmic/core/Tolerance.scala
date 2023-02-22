package com.nigeleke.ohmic
package core

enum Tolerance(bandColour: BandColour):
  case OnePercent  extends Tolerance(BandColour.Brown)
  case TwoPercent  extends Tolerance(BandColour.Red)
  case FivePercent extends Tolerance(BandColour.Gold)
  case TenPercent  extends Tolerance(BandColour.Silver)
