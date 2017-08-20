package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.hasSize
import ch.tutteli.atrium.isEmpty

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<List<Int>>::hasSize.name to IAssertionPlant<List<Int>>::hasSize,
    IAssertionPlant<List<Int>>::isEmpty.name to IAssertionPlant<List<Int>>::isEmpty
)
