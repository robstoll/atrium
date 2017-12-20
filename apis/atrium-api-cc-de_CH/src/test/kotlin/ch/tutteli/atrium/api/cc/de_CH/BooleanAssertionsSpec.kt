package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.assertions.BooleanAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<Boolean>::istTrue.name to AssertionPlant<Boolean>::istTrue,
    AssertionPlant<Boolean>::istFalse.name to AssertionPlant<Boolean>::istFalse
)
