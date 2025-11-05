package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableToContainInAnyOrderAtLeast1ValuesExpectationsBuilderTest :
    AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest(
        functionDescription to Companion::toContainValues,
        (functionDescription to Companion::toContainNullableValues).withNullableSuffix(),
    ) {
    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$atLeast(1).$value/$values"

        private fun toContainValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.atLeast(1).value(a)
            else expect.toContain.inAnyOrder.atLeast(1).values(a, *aX)

        private fun toContainNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.atLeast(1).value(a)
            else expect.toContain.inAnyOrder.atLeast(1).values(a, *aX)
    }

    @Suppress("AssignedValueIsNeverRead", "unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, null, 1.2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2, 2.2))
        var star: Expect<Collection<*>> = expect(listOf(1, null, 1.2, "asdf"))

        list = list.toContain.inAnyOrder.atLeast(1).value(1)
        nSet = nSet.toContain.inAnyOrder.atLeast(1).value(1)
        subList = subList.toContain.inAnyOrder.atLeast(1).value(1)
        star = star.toContain.inAnyOrder.atLeast(1).value(1)

        list = list.toContain.inAnyOrder.atLeast(1).values(1, 1.2)
        nSet = nSet.toContain.inAnyOrder.atLeast(1).values(1, 1.2)
        subList = subList.toContain.inAnyOrder.atLeast(1).values(1, 2.2)
        star = star.toContain.inAnyOrder.atLeast(1).values(1, 1.2, "asdf")
    }

}
