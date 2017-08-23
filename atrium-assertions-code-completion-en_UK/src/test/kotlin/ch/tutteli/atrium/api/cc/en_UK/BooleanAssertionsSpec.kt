package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.assertions.BooleanAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<Boolean>::isTrue.name to IAssertionPlant<Boolean>::isTrue,
    IAssertionPlant<Boolean>::isFalse.name to IAssertionPlant<Boolean>::isFalse
)
