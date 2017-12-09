package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.IAssertionPlant

class NumberAssertionsSpec : ch.tutteli.atrium.spec.assertions.NumberAssertionsSpec(
    AssertionVerbFactory,
    IAssertionPlant<Int>::isLessThan.name to Companion::isLessThan,
    IAssertionPlant<Int>::isLessOrEquals.name to Companion::isLessOrEquals,
    IAssertionPlant<Int>::isGreaterThan.name to Companion::isGreaterThan,
    IAssertionPlant<Int>::isGreaterOrEquals.name to Companion::isGreaterOrEquals
) {
    companion object {
        fun isLessThan(plant: IAssertionPlant<Int>, expected: Int)
            = plant isLessThan expected

        fun isLessOrEquals(plant: IAssertionPlant<Int>, expected: Int)
            = plant isLessOrEquals expected

        fun isGreaterThan(plant: IAssertionPlant<Int>, expected: Int)
            = plant isGreaterThan expected

        fun isGreaterOrEquals(plant: IAssertionPlant<Int>, expected: Int)
            = plant isGreaterOrEquals expected
    }
}
