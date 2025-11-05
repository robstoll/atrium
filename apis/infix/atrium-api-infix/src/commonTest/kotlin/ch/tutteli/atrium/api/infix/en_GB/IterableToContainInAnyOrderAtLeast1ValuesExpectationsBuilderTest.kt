package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest
import kotlin.test.Test

class IterableToContainInAnyOrderAtLeast1ValuesExpectationsBuilderTest :
    AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest(
        getToContainPair(),
        getToContainNullablePair()
    ) {
    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderValues" to Companion::toContainValues

        private fun toContainValues(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast 1 value a
            else expect toContain o inAny order atLeast 1 the values(a, *aX)

        fun getToContainNullablePair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderValues" to Companion::toContainNullableValues

        private fun toContainNullableValues(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast 1 value a
            else expect toContain o inAny order atLeast 1 the values(a, *aX)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, null, 1.2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2, 2.2))
        var star: Expect<Collection<*>> = expect(listOf(1, null, 1.2, "asdf"))

        list = list toContain o inAny order atLeast 1 value 1
        nSet = nSet toContain o inAny order atLeast 1 value 1
        subList = subList toContain o inAny order atLeast 1 value 1
        star = star toContain o inAny order atLeast 1 value 1

        list = list toContain o inAny order atLeast 1 the values(1, 1.2)
        nSet = nSet toContain o inAny order atLeast 1 the values(1, 1.2)
        subList = subList toContain o inAny order atLeast 1 the values(1, 2.2)
        star = star toContain o inAny order atLeast 1 the values(1, 1.2, "asdf")
    }
}
