package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries

class IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedEntriesAssertionsSpec(
    AssertionVerbFactory,
    getContainsPair(),
    Companion::groupFactory,
    getContainsNullablePair(),
    Companion::nullableGroupFactory,
    "* ", "(/) ", "(x) ", "(!) ", "- ", "» ", ">> ", "=> ",
    "[Atrium][Builder] "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair()
            = "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderEntries

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

        fun getContainsNullablePair()
            = "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder nullable" to Companion::containsInOrderOnlyGroupedNullableEntriesPair

        private fun containsInOrderOnlyGroupedNullableEntriesPair(
            plant: Assert<Iterable<Double?>>,
            a1: GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>,
            a2: GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>,
            aX: Array<out GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>>
        ): Assert<Iterable<Double?>> {
            return plant.enthaelt.inGegebenerReihenfolge.nur.gruppiert.innerhalb.inBeliebigerReihenfolge(a1, a2, *aX)
        }

        private fun nullableGroupFactory(groups: Array<out (Assert<Double>.() -> Unit)?>): GroupWithNullableEntries<(Assert<Double>.() -> Unit)?> {
            return when(groups.size){
                0 -> object: GroupWithNullableEntries<(Assert<Double>.() -> Unit)?>{ override fun toList() = listOf<Assert<Double>.() -> Unit>() }
                1 -> NullableEintrag(groups[0])
                else -> NullableEintraege(groups[0], *groups.drop(1).toTypedArray())
            }
        }
    }
}
