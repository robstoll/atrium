package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

object NumberAssertionsSpec : ch.tutteli.atrium.spec.assertions.NumberAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<Int>::istKleinerAls.name to IAssertionPlant<Int>::istKleinerAls,
    IAssertionPlant<Int>::istKleinerOderGleich.name to IAssertionPlant<Int>::istKleinerOderGleich,
    IAssertionPlant<Int>::istGroesserAls.name to IAssertionPlant<Int>::istGroesserAls,
    IAssertionPlant<Int>::istGroesserOderGleich.name to IAssertionPlant<Int>::istGroesserOderGleich
)
