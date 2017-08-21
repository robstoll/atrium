package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.hatDieGroesse
import ch.tutteli.atrium.istLeer

object CollectionAssertionsSpec : ch.tutteli.atrium.spec.assertions.CollectionAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<List<Int>>::hatDieGroesse.name to IAssertionPlant<List<Int>>::hatDieGroesse,
    IAssertionPlant<List<Int>>::istLeer.name to IAssertionPlant<List<Int>>::istLeer
)
