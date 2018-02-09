package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.integration.CollectionAssertionsSpec(
    AssertionVerbFactory,
    Assert<List<Int>>::hatDieGroesse.name to Assert<List<Int>>::hatDieGroesse,
    Assert<List<Int>>::istLeer.name to Assert<List<Int>>::istLeer,
    Assert<List<Int>>::istNichtLeer.name to Assert<List<Int>>::istNichtLeer
)
