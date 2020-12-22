package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.utils.Group
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.specs.withNullableSuffix

class IterableContainsInOrderOnlyGroupedValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInOrderOnlyGroupedValuesAssertionsSpec(
        functionDescription to Companion::containsInOrderOnlyGroupedInAnyOrderValues,
        Companion::groupFactory,
        (functionDescription to Companion::containsInOrderOnlyGroupedInAnyOrderNullableValues).withNullableSuffix(),
        Companion::nullableGroupFactory,
        "◆ ", "✔ ", "✘ ", "❗❗ ", "⚬ ", "» ", "▶ ", "◾ ",
        "[Atrium][Builder] "
    ) {
    companion object : IterableContainsSpecBase() {
        val functionDescription =  "$contains.$inOrder.$only.$grouped.$within.$withinInAnyOrder"

        private fun containsInOrderOnlyGroupedInAnyOrderValues(
            expect: Expect<Iterable<Double>>,
            a1: Group<Double>,
            a2: Group<Double>,
            aX: Array<out Group<Double>>
        ): Expect<Iterable<Double>> = expect.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)

        private fun groupFactory(groups: Array<out Double>): Group<Double> =
            when (groups.size) {
                0 -> object : Group<Double> {
                    override fun toList() = listOf<Double>()
                }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }

        private fun containsInOrderOnlyGroupedInAnyOrderNullableValues(
            expect: Expect<Iterable<Double?>>,
            a1: Group<Double?>,
            a2: Group<Double?>,
            aX: Array<out Group<Double?>>
        ): Expect<Iterable<Double?>> = expect.contains.inOrder.only.grouped.within.inAnyOrder(a1, a2, *aX)

        private fun nullableGroupFactory(groups: Array<out Double?>): Group<Double?> =
            when (groups.size) {
                0 -> object : Group<Double?> {
                    override fun toList() = listOf<Double>()
                }
                1 -> Value(groups[0])
                else -> Values(groups[0], *groups.drop(1).toTypedArray())
            }
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2))
        nList = nList.contains.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2))
        subList = subList.contains.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2))
        star = star.contains.inOrder.only.grouped.within.inAnyOrder(Value(1), Values(1, 2))

        nList = nList.contains.inOrder.only.grouped.within.inAnyOrder(Value(null), Values(1, null))
        star = star.contains.inOrder.only.grouped.within.inAnyOrder(Value(null), Values(null, 2))
    }
}
