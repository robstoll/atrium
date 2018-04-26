package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.integration.BooleanAssertionsSpec(
    AssertionVerbFactory,
    Assert<Boolean>::isTrue.name to Assert<Boolean>::isTrue,
    Assert<Boolean>::isFalse.name to Assert<Boolean>::isFalse
)
