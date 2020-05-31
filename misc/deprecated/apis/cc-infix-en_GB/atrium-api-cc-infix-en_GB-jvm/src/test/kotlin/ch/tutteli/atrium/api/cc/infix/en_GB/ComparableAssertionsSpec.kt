// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.infix.en_GB.isGreaterThan
import ch.tutteli.atrium.api.infix.en_GB.isGreaterThanOrEqual
import ch.tutteli.atrium.api.infix.en_GB.isLessThan
import ch.tutteli.atrium.api.infix.en_GB.isLessThanOrEqual
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect

//TODO remove with 1.0.0, no need to migrate to Spek 2
class ComparableAssertionsSpec : ch.tutteli.atrium.spec.integration.ComparableAssertionsSpec(
    AssertionVerbFactory,
    Assert<Int>::isLessThan.name to Companion::isLessThan,
    Assert<Int>::isLessOrEquals.name to Companion::isLessOrEquals,
    Assert<Int>::isGreaterThan.name to Companion::isGreaterThan,
    Assert<Int>::isGreaterOrEquals.name to Companion::isGreaterOrEquals
) {
    companion object {
        fun isLessThan(plant: Assert<Int>, expected: Int)
            = plant.asExpect().isLessThan(expected).asAssert()

        fun isLessOrEquals(plant: Assert<Int>, expected: Int)
            = plant.asExpect().isLessThanOrEqual(expected).asAssert()

        fun isGreaterThan(plant: Assert<Int>, expected: Int)
            = plant.asExpect().isGreaterThan(expected).asAssert()

        fun isGreaterOrEquals(plant: Assert<Int>, expected: Int)
            = plant.asExpect().isGreaterThanOrEqual(expected).asAssert()
    }
}
