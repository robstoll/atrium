package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object ComparableAssertionsSpec : ch.tutteli.atrium.spec.assertions.ComparableAssertionsSpec(
    AssertionVerbFactory,
    Assert<Int>::istKleinerAls.name to Assert<Int>::istKleinerAls,
    Assert<Int>::istKleinerOderGleich.name to Assert<Int>::istKleinerOderGleich,
    Assert<Int>::istGroesserAls.name to Assert<Int>::istGroesserAls,
    Assert<Int>::istGroesserOderGleich.name to Assert<Int>::istGroesserOderGleich
)
