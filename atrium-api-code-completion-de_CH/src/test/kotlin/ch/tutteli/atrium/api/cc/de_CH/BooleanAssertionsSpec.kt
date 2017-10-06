package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.assertions.BooleanAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<Boolean>::istTrue.name to IAssertionPlant<Boolean>::istTrue,
    IAssertionPlant<Boolean>::istFalse.name to IAssertionPlant<Boolean>::istFalse
)
