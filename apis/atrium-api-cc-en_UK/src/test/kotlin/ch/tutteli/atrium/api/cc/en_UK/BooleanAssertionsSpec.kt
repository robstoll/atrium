@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object BooleanAssertionsSpec : ch.tutteli.atrium.spec.integration.BooleanAssertionsSpec(
    AssertionVerbFactory,
    Assert<Boolean>::isTrue.name to Assert<Boolean>::isTrue,
    Assert<Boolean>::isFalse.name to Assert<Boolean>::isFalse
)
