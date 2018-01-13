package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant

class ComparableAssertionsSpec : ch.tutteli.atrium.spec.assertions.ComparableAssertionsSpec(
    AssertionVerbFactory,
    AssertionPlant<Int>::isLessThan.name to Companion::isLessThan,
    AssertionPlant<Int>::isLessOrEquals.name to Companion::isLessOrEquals,
    AssertionPlant<Int>::isGreaterThan.name to Companion::isGreaterThan,
    AssertionPlant<Int>::isGreaterOrEquals.name to Companion::isGreaterOrEquals
) {
    companion object {
        fun isLessThan(plant: AssertionPlant<Int>, expected: Int)
            = plant isLessThan expected

        fun isLessOrEquals(plant: AssertionPlant<Int>, expected: Int)
            = plant isLessOrEquals expected

        fun isGreaterThan(plant: AssertionPlant<Int>, expected: Int)
            = plant isGreaterThan expected

        fun isGreaterOrEquals(plant: AssertionPlant<Int>, expected: Int)
            = plant isGreaterOrEquals expected
    }
}
