package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<List<Int>>::hatDieGroesse.name to IAssertionPlant<List<Int>>::hatDieGroesse,
    IAssertionPlant<List<Int>>::istLeer.name to IAssertionPlant<List<Int>>::istLeer,
    IAssertionPlant<List<Int>>::istNichtLeer.name to IAssertionPlant<List<Int>>::istNichtLeer
)
