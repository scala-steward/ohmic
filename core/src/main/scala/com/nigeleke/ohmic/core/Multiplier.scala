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
