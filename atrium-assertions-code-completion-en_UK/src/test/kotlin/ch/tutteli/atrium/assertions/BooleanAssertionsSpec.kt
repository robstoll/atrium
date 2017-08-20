package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.isFalse
import ch.tutteli.atrium.isTrue

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.assertions.BooleanAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<Boolean>::isTrue.name to IAssertionPlant<Boolean>::isTrue,
    IAssertionPlant<Boolean>::isFalse.name to IAssertionPlant<Boolean>::isFalse
)
