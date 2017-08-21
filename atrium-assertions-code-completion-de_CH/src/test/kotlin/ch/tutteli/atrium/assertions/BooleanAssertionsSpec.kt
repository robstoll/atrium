package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.istFalse
import ch.tutteli.atrium.istTrue

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.assertions.BooleanAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<Boolean>::istTrue.name to IAssertionPlant<Boolean>::istTrue,
    IAssertionPlant<Boolean>::istFalse.name to IAssertionPlant<Boolean>::istFalse
)
