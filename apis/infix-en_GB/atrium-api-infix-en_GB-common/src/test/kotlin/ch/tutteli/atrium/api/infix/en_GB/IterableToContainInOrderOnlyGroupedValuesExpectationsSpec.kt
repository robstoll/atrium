package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group

class IterableToContainInOrderOnlyGroupedValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInOrderOnlyGroupedValuesExpectationsSpec(
        getContainsPair(),
        Companion::groupFactory,
        getContainsNullablePair(),
        Companion::nullableGroupFactory,
        "[Atrium][Builder] "
    ) {
    companion object : IterableToContainSpecBase() {
        fun getContainsPair() =
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to Companion::toContainInOrderOnlyGroupedInAnyOrderValues

        private fun toContainInOrderOnlyGroupedInAnyOrderValues(
            expect: Expect<Iterable<Double>>,
            a1: Group<Double>,
            a2: Group<Double>,
            aX: Array<out Group<Double>>
        ): Expect<Iterable<Double>> =
            expect toContain o inGiven order and only grouped entries within group inAny order(a1, a2, *aX)

        private fun groupFactory(groups: Array<out Double>): Group<Double> =
            when (groups.size) {
                0 -> object : Group<Double> {
                    override fun toList() = listOf<Double>()
                }
                1 -> value(groups[0])
                else -> values(groups[0], *groups.drop(1).toTypedArray())
            }


        fun getContainsNullablePair() =
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to Companion::toContainInOrderOnlyGroupedInAnyOrderNullableValues

        private fun toContainInOrderOnlyGroupedInAnyOrderNullableValues(
            expect: Expect<Iterable<Double?>>,
            a1: Group<Double?>,
            a2: Group<Double?>,
            aX: Array<out Group<Double?>>
        ): Expect<Iterable<Double?>> =
            expect toContain o inGiven order and only grouped entries within group inAny order(a1, a2, *aX)

        private fun nullableGroupFactory(groups: Array<out Double?>): Group<Double?> =
            when (groups.size) {
                0 -> object : Group<Double?> {
                    override fun toList() = listOf<Double>()
                }
                1 -> value(groups[0])
                else -> values(groups[0], *groups.drop(1).toTypedArray())
            }
    }
}
