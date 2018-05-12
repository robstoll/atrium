package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.Group

class IterableContainsInOrderOnlyGroupedNullableSpec : ch.tutteli.atrium.spec.integration.IterableContainsInOrderOnlyGroupedNullableSpec(
    AssertionVerbFactory,
    getContainsInOrderOnlyGroupedNullableEntriesPair(),
    "* ", "(/) ", "(x) ", "- ", "» ", ">> ", "=> "
) {
    companion object : IterableContainsSpecBase() {
        fun getContainsInOrderOnlyGroupedNullableEntriesPair() =
            "$contains.$inOrder.$only.$inOrderOnlyEntries.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedNullableEntriesPair

        private fun containsInOrderOnlyGroupedNullableEntriesPair(
            plant: Assert<Iterable<Double?>>,
            a1: Group<(Assert<Double>.() -> Unit)?>,
            a2: Group<(Assert<Double>.() -> Unit)?>,
            aX: Array<out Group<(Assert<Double>.() -> Unit)?>>
        ): Assert<Iterable<Double?>> {
            return plant.enthaelt.inGegebenerReihenfolge.nur.gruppiert.innerhalb.inBeliebigerReihenfolge(a1, a2, *aX)
        }
    }
}
