@file:Suppress("DEPRECATION" /* will be removed with 1.0.0 */)
package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group

class IterableContainsInOrderOnlyGroupedValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedValuesAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    getContainsNullablePair(),
    Companion::nullableGroupFactory,
    "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderValues

        private fun containsInOrderOnlyGroupedInAnyOrderValues(
            plant: Assert<Iterable<Double>>,
            a1: Group<Double>,
            a2: Group<Double>,
            aX: Array<out Group<Double>>
        ): Assert<Iterable<Double>> {
            return plant.enthaelt.inGegebenerReihenfolge.nur.gruppiert.innerhalb.inBeliebigerReihenfolge(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out Double>): Group<Double> {
            return when (groups.size) {
                0 -> object : Group<Double> { override fun toList() = listOf<Double>() }
                1 -> Wert(groups[0])
                else -> Werte(groups[0], *groups.drop(1).toTypedArray())
            }
        }

        fun getContainsNullablePair()
            = "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder nullable" to Companion::containsInOrderOnlyGroupedInAnyOrderNullableValues

        private fun containsInOrderOnlyGroupedInAnyOrderNullableValues(
            plant: Assert<Iterable<Double?>>,
            a1: Group<Double?>,
            a2: Group<Double?>,
            aX: Array<out Group<Double?>>
        ): Assert<Iterable<Double?>> {
            return plant.enthaelt.inGegebenerReihenfolge.nur.gruppiert.innerhalb.inBeliebigerReihenfolge(a1, a2, *aX)
        }

        private fun nullableGroupFactory(groups: Array<out Double?>): Group<Double?> {
            return when (groups.size) {
                0 -> object : Group<Double?> { override fun toList() = listOf<Double>() }
                1 -> Wert(groups[0])
                else -> Werte(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}

