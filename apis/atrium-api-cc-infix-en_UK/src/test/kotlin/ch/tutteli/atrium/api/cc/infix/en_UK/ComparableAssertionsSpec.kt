@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class ComparableAssertionsSpec : ch.tutteli.atrium.spec.integration.ComparableAssertionsSpec(
    AssertionVerbFactory,
    Assert<Int>::isLessThan.name to Companion::isLessThan,
    Assert<Int>::isLessOrEquals.name to Companion::isLessOrEquals,
    Assert<Int>::isGreaterThan.name to Companion::isGreaterThan,
    Assert<Int>::isGreaterOrEquals.name to Companion::isGreaterOrEquals
) {
    companion object {
        fun isLessThan(plant: Assert<Int>, expected: Int)
            = plant isLessThan expected

        fun isLessOrEquals(plant: Assert<Int>, expected: Int)
            = plant isLessOrEquals expected

        fun isGreaterThan(plant: Assert<Int>, expected: Int)
            = plant isGreaterThan expected

        fun isGreaterOrEquals(plant: Assert<Int>, expected: Int)
            = plant isGreaterOrEquals expected
    }
}
