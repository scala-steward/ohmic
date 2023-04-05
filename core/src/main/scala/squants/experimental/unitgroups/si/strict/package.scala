package squants.experimental.unitgroups.si

import squants.{Dimension, Quantity}
import squants.{SiUnit, UnitOfMeasure}
import squants.experimental.unitgroups.UnitGroup

/** UnitGroups for SI.
  */
package object strict:

  /** implicit support for SI unit groups */
  given [A <: Quantity[A]](using dimension: Dimension[A]): UnitGroup[A] =
    new UnitGroup[A]:
      val units: Set[UnitOfMeasure[A]] = dimension.units.filter(_.isInstanceOf[SiUnit])
