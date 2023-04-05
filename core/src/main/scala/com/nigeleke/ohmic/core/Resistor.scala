/*
 * Copyright (c) 2023, Nigel Eke
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.nigeleke.ohmic
package core

import squants.Quantity
import squants.electro.*
import squants.experimental.formatter.DefaultFormatter
import squants.experimental.unitgroups.UnitGroup
import squants.experimental.unitgroups.si.strict.given

final case class Resistor(base: Base, multiplier: Multiplier)

object Resistor:
  def setFor(series: Seq[Base]) = (for
    base       <- series
    multiplier <- Multiplier.values
  yield Resistor(base, multiplier)).toSet

  lazy val fullSet =
    val emptySet           = Set.empty[Resistor]
    val unionSeriesWithSet = (set: Set[Resistor], series: Seq[Base]) => set ++ setFor(series)
    Series.all.foldLeft(emptySet)(unionSeriesWithSet)

  extension (r: Resistor)
    def value: ElectricalResistance = Ohms(r.multiplier(r.base))

    def formattedValue: ElectricalResistance =
      val unitGroup = summon[UnitGroup[ElectricalResistance]]
      val formatter = DefaultFormatter(unitGroup)
      formatter.inBestUnit(value)

    def colourCodes: Seq[BandColour] =
      val baseValueString  = r.base.toString
      val bandColours      = baseValueString.map(BandColour.from)
      val multiplierColour = r.multiplier.bandColour
      bandColours :+ multiplierColour
