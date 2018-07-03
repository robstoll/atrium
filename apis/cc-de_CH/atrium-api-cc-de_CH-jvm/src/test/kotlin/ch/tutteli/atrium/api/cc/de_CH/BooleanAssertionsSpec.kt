package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.integration.BooleanAssertionsSpec(
    AssertionVerbFactory,
    Assert<Boolean>::istTrue.name to Assert<Boolean>::istTrue,
    Assert<Boolean>::istFalse.name to Assert<Boolean>::istFalse
)
