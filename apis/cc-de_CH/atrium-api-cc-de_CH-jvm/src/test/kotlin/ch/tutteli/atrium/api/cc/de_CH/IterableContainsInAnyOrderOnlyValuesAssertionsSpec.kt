package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert

class IterableContainsInAnyOrderOnlyValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInAnyOrderOnlyValuesAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    getContainsNullablePair(),
    "* ", "(/) ", "(x) ", "(!) ", "- "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues" to Companion::containsInAnyOrderOnlyValues

        private fun containsInAnyOrderOnlyValues(plant: Assert<Iterable<Double>>, a: Double, aX: Array<out Double>): Assert<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.nur.wert(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.nur.werte(a, *aX)
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inAnyOrder.$only.$inAnyOrderOnlyValues nullable" to Companion::containsInAnyOrderOnlyNullableValues

        private fun containsInAnyOrderOnlyNullableValues(plant: Assert<Iterable<Double?>>, a: Double?, aX: Array<out Double?>): Assert<Iterable<Double?>> {
            return if (aX.isEmpty()) {
                plant.enthaelt.inBeliebigerReihenfolge.nur.nullableWert(a)
            } else {
                plant.enthaelt.inBeliebigerReihenfolge.nur.nullableWerte(a, *aX)
            }
        }
    }
}
