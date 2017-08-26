package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

object NumberAssertionsSpec : ch.tutteli.atrium.spec.assertions.NumberAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<Int>::isLessThan.name to IAssertionPlant<Int>::isLessThan,
    IAssertionPlant<Int>::isLessOrEquals.name to IAssertionPlant<Int>::isLessOrEquals,
    IAssertionPlant<Int>::isGreaterThan.name to IAssertionPlant<Int>::isGreaterThan,
    IAssertionPlant<Int>::isGreaterOrEquals.name to IAssertionPlant<Int>::isGreaterOrEquals
)
