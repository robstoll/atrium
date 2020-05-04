// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.api.infix.en_GB.contains
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.migration.asExpect
import ch.tutteli.atrium.api.infix.en_GB.o

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

        private fun containsNotOrAtMost(plant: Assert<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>)
            = plant.asExpect().contains(o) inAny order notOrAtMost atMost the Values(a, *aX)

        private fun getContainsNotPair() = containsNotValues to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int)
            = "use `$containsNotValues` instead of `$notOrAtMost $times`"

    }
}
