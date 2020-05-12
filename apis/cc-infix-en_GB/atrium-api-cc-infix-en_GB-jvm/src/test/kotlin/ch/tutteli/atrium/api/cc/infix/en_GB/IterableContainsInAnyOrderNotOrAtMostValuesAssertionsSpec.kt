// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.api.infix.en_GB.contains
import ch.tutteli.atrium.api.infix.en_GB.notOrAtMost
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.the
import ch.tutteli.atrium.api.infix.en_GB.values
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.migration.asAssert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory

//TODO remove with 1.0.0, no need to migrate to Spek 2
class IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderNotOrAtMostValuesAssertionsSpec(
    AssertionVerbFactory,
    getNotOrAtMostTriple(),
    getContainsNotPair(),
    "◆ "
) {

    companion object : IterableContainsSpecBase() {

        private fun getNotOrAtMostTriple() = Triple(
            "$toContain $notOrAtMost",
            { what: String, times: String -> "$toContain $what $notOrAtMost $times" },
            Companion::containsNotOrAtMost
        )

        private fun containsNotOrAtMost(plant: Assert<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>): AssertionPlant<Iterable<Double>> {
            val values = Values(a, *aX)
            return (plant.asExpect().contains(o) inAny order).notOrAtMost(atMost)
                .the<Double, Iterable<Double>>(values(values.expected, *values.otherExpected)).asAssert()
        }

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use `$containsNotValues` instead of `$notOrAtMost $times`"

    }
}
