package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<List<Int>>::hasSize.name to IAssertionPlant<List<Int>>::hasSize,
    IAssertionPlant<List<Int>>::isEmpty.name to IAssertionPlant<List<Int>>::isEmpty
)
