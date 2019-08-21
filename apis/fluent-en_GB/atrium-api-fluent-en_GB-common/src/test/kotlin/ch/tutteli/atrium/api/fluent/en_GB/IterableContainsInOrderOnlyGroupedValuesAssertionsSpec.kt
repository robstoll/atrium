package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.AssertionVerbFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group

class IterableContainsInOrderOnlyGroupedValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyGroupedValuesAssertionsSpec(
        AssertionVerbFactory,
        getContainsPair(),
        Companion::groupFactory,
        getContainsNullablePair(),
        Companion::nullableGroupFactory,
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    ) {
    companion object : IterableContainsSpecBase() {
        fun getContainsPair() =
            "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderValues

        private fun containsInOrderOnlyGroupedInAnyOrderValues(
            expect: Expect<Iterable<Double>>,
            a1: Group<Double>,
            a2: Group<Double>,
            aX: Array<out Group<Double>>
        ): Expect<Iterable<Double>> {
            return expect.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
        }

        private fun groupFactory(groups: Array<out Double>): Group<Double> =
            when (groups.size) {
                0 -> object : Group<Double> {
                    override fun toList() = listOf<Double>()
                }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }


        fun getContainsNullablePair() =
            "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder" to Companion::containsInOrderOnlyGroupedInAnyOrderNullableValues

        private fun containsInOrderOnlyGroupedInAnyOrderNullableValues(
            expect: Expect<Iterable<Double?>>,
            a1: Group<Double?>,
            a2: Group<Double?>,
            aX: Array<out Group<Double?>>
        ): Expect<Iterable<Double?>> {
            return expect.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)
        }

        private fun nullableGroupFactory(groups: Array<out Double?>): Group<Double?> =
            when (groups.size) {
                0 -> object : Group<Double?> {
                    override fun toList() = listOf<Double>()
                }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }
    }
}
