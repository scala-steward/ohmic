package com.nigeleke.resistors.core

import scala.language.implicitConversions

sealed trait Tolerance

object Tolerance {

    case object OnePercent extends Tolerance
    case object TwoPercent extends Tolerance
    case object FivePercent extends Tolerance
    case object TenPercent extends Tolerance

    implicit def toBandColour(tolerance: Tolerance) : BandColour = tolerance match {
        case OnePercent => BandColour.Brown
        case TwoPercent => BandColour.Red
        case FivePercent => BandColour.Gold
        case TenPercent => BandColour.Silver
    }

}