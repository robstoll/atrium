package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries

class IterableContainsInOrderOnlyGroupedValuesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedValuesAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    "* ", "(/) ", "(x) ", "(!) ", "- ", ">> ", "=> ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderValues

        private fun containsInOrderOnlyGroupedInAnyOrderValues(
            plant: Assert<Iterable<Double>>,
            a1: GroupWithoutNullableEntries<Double>,
            a2: GroupWithoutNullableEntries<Double>,
            aX: Array<out GroupWithoutNullableEntries<Double>>
        ): Assert<Iterable<Double>> {
            return plant.enthaelt.inGegebenerReihenfolge.nur.gruppiert.innerhalb.inBeliebigerReihenfolge(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out Double>): GroupWithoutNullableEntries<Double> {
            return when (groups.size) {
                0 -> object : GroupWithoutNullableEntries<Double> { override fun toList() = listOf<Double>() }
                1 -> Wert(groups[0])
                else -> Werte(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}

