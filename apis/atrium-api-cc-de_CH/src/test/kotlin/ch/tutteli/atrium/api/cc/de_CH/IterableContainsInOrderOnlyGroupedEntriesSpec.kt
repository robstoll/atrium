package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries

class IterableContainsInOrderOnlyGroupedEntriesSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedEntriesSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderEntries

        private fun containsInOrderOnlyGroupedInAnyOrderEntries(
            plant: Assert<Iterable<Double>>,
            a1: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>,
            a2: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>,
            aX: Array<out GroupWithoutNullableEntries<Assert<Double>.() -> Unit>>
        ): Assert<Iterable<Double>> {
            return plant.enthaelt.inGegebenerReihenfolge.nur.gruppiert.innerhalb.inBeliebigerReihenfolge(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out Assert<Double>.() -> Unit>): GroupWithoutNullableEntries<Assert<Double>.() -> Unit> {
            return when(groups.size){
                0 -> object: GroupWithoutNullableEntries<Assert<Double>.() -> Unit>{ override fun toList() = listOf<Assert<Double>.() -> Unit>() }
                1 -> Eintrag(groups[0])
                else -> Eintraege(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}
