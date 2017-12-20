package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

object NumberAssertionsSpec : ch.tutteli.atrium.spec.assertions.NumberAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<Int>::istKleinerAls.name to AssertionPlant<Int>::istKleinerAls,
    AssertionPlant<Int>::istKleinerOderGleich.name to AssertionPlant<Int>::istKleinerOderGleich,
    AssertionPlant<Int>::istGroesserAls.name to AssertionPlant<Int>::istGroesserAls,
    AssertionPlant<Int>::istGroesserOderGleich.name to AssertionPlant<Int>::istGroesserOderGleich
)
