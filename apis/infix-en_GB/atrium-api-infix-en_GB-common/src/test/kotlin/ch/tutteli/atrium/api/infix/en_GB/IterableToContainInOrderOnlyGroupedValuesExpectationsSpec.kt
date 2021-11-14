package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.utils.Group
import ch.tutteli.atrium.specs.notImplemented

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
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to Companion::toContainInOrderOnlyGroupedInAnyOrdervalues

        private fun toContainInOrderOnlyGroupedInAnyOrdervalues(
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
            "$toContain $filler $inOrder $andOnly $grouped $within $withinInAnyOrder" to Companion::toContainInOrderOnlyGroupedInAnyOrderNullablevalues

        private fun toContainInOrderOnlyGroupedInAnyOrderNullablevalues(
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

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number>(1, 2.2)
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number>(1, 2.2)
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number>(1, 2.2)
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number>(1, 2.2)
        )

        list = list toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )
        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )
        subList = subList toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(1),
            values(1, 2),
            report = {}
        )

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1, null),
            //TODO check if <Number> is still necessary with kotlin 1.4, if so, report a bug
            values<Number?>(1.2, null)
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2, 1.2)
        )

        nList = nList toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(1, null),
            report = {}
        )
        star = star toContain o inGiven order and only grouped entries within group inAny order(
            value(null),
            values(null, 2),
            report = {}
        )
    }
}
