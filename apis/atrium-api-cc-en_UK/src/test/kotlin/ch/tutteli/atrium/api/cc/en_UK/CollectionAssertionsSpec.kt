package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<List<Int>>::hasSize.name to AssertionPlant<List<Int>>::hasSize,
    AssertionPlant<List<Int>>::isEmpty.name to AssertionPlant<List<Int>>::isEmpty,
    AssertionPlant<List<Int>>::isNotEmpty.name to AssertionPlant<List<Int>>::isNotEmpty
)
