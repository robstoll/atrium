package ch.tutteli.atrium.api.cc.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

object NumberAssertionsSpec : ch.tutteli.atrium.spec.assertions.NumberAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<Int>::isLessThan.name to AssertionPlant<Int>::isLessThan,
    AssertionPlant<Int>::isLessOrEquals.name to AssertionPlant<Int>::isLessOrEquals,
    AssertionPlant<Int>::isGreaterThan.name to AssertionPlant<Int>::isGreaterThan,
    AssertionPlant<Int>::isGreaterOrEquals.name to AssertionPlant<Int>::isGreaterOrEquals
)
