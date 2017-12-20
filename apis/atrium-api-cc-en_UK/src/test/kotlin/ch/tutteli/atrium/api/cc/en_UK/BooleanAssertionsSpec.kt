package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.assertions.BooleanAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<Boolean>::isTrue.name to AssertionPlant<Boolean>::isTrue,
    AssertionPlant<Boolean>::isFalse.name to AssertionPlant<Boolean>::isFalse
)
