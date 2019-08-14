@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.integration.CollectionAssertionsSpec(
    AssertionVerbFactory,
    Assert<Collection<Int>>::hatDieGroesse.name to Assert<Collection<Int>>::hatDieGroesse,
    Assert<Collection<Int>>::istLeer.name to Assert<Collection<Int>>::istLeer,
    Assert<Collection<Int>>::istNichtLeer.name to Assert<Collection<Int>>::istNichtLeer
)
