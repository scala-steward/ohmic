package com.nigeleke.resistors.core

import scala.language.implicitConversions

sealed trait Band

object Band {

    case object Black extends Band
    case object Brown extends Band
    case object Red extends Band
    case object Orange extends Band
    case object Yellow extends Band
    case object Green extends Band
    case object Blue extends Band
    case object Violet extends Band
    case object Grey extends Band
    case object White extends Band

    implicit def toBandColour(band: Band) : BandColour = band match {
        case Black => BandColour.Black
        case Brown => BandColour.Brown
        case Red => BandColour.Red
        case Orange => BandColour.Orange
        case Yellow => BandColour.Yellow
        case Green => BandColour.Green
        case Blue => BandColour.Blue
        case Violet => BandColour.Violet
        case Grey => BandColour.Grey
        case White => BandColour.White
    }

    implicit def from(c: Char) : Band = fromMap(c)

    private val fromMap : Map[Char, Band] = Map(
        ('0', Black), ('1', Brown), ('2', Red),    ('3', Orange), ('4', Yellow),
        ('5', Green), ('6', Blue),  ('7', Violet), ('8', Grey),   ('9', White))

}
