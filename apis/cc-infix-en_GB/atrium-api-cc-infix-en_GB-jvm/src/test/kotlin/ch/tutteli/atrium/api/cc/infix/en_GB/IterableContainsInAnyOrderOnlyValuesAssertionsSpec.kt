// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")
package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.contain
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.only
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.order
import ch.tutteli.atrium.api.infix.en_GB.contains
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.domain.builders.migration.asExpect

//TODO remove with 1.0.0, no need to migrate to Spek 2
class IterableContainsInAnyOrderOnlyValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNullablePair(),
    "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$toContain $inAnyOrder $butOnly $inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnlyValues

        private fun containsInAnyOrderOnlyValues(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.asExpect().contains(o) inAny order but only value a
            } else {
                plant.asExpect().contains(o) inAny order but only the Values(a, *aX)
            }
        }

        fun getContainsNullablePair() =
            "$toContain $inAnyOrder $butOnly $inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnlyNullableValues

        private fun containsInAnyOrderOnlyNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.asExpect().contains(o) inAny order but only value(a)
            } else {
                plant.asExpect().contains(o) inAny order but only the Values(a, *aX)
            }
        }
    }
}
