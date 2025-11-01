package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest
import kotlin.test.Test

class IterableToContainInAnyOrderAtLeast1EntriesExpectationsBuilderTest :
    AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest(
        getToContainPair(),
        getToContainNullablePair(),
    ) {
    companion object : IterableToContainSpecBase() {
        fun getToContainPair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::toContainInAnyOrderEntries

        private fun toContainInAnyOrderEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast 1 entry a
            else expect toContain o inAny order atLeast 1 the entries(a, *aX)

        fun getToContainNullablePair() =
            "$toContain $filler $inAnyOrder $atLeast 1 $inAnyOrderEntries" to Companion::toContainNullableEntries

        private fun toContainNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain o inAny order atLeast 1 entry a
            else expect toContain o inAny order atLeast 1 the entries(a, *aX)

    }

    @Suppress("AssignedValueIsNeverRead", "unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1, null))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1, null))

        list = list toContain o inAny order atLeast 1 entry { it toEqual 1 }
        nList = nList toContain o inAny order atLeast 1 entry { it toEqual 1 }
        subList = subList toContain o inAny order atLeast 1 entry { it toEqual 1 }
        star = star toContain o inAny order atLeast 1 entry { it toEqual 1 }

        nList = nList toContain o inAny order atLeast 1 entry null
        star = star toContain o inAny order atLeast 1 entry null
    }
}
