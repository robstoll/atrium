package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<List<Int>>::hatDieGroesse.name to AssertionPlant<List<Int>>::hatDieGroesse,
    AssertionPlant<List<Int>>::istLeer.name to AssertionPlant<List<Int>>::istLeer,
    AssertionPlant<List<Int>>::istNichtLeer.name to AssertionPlant<List<Int>>::istNichtLeer
)
