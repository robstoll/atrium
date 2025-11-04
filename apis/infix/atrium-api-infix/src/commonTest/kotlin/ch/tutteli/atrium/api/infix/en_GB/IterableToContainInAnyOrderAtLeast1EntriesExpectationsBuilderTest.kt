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
        var nSet: Expect<Set<Number?>> = expect(setOf(1, null))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1, null))

        list = list toContain o inAny order atLeast 1 entry { it toEqual 1 }
        nSet = nSet toContain o inAny order atLeast 1 entry { it toEqual 1 }
        subList = subList toContain o inAny order atLeast 1 entry { it toEqual 1 }
        star = star toContain o inAny order atLeast 1 entry { it toEqual 1 }

        nSet = nSet toContain o inAny order atLeast 1 entry null
        star = star toContain o inAny order atLeast 1 entry null

        list = list toContain o inAny order atLeast 1 the entries({ it toEqual 1 }, { it notToEqual 2 })
        nSet = nSet toContain o inAny order atLeast 1 the entries({ toEqual(1) }, null)
        subList = subList toContain o inAny order atLeast 1 the entries({ it toEqual 1 }, { it notToEqual 2 })
        star = star toContain o inAny order atLeast 1 the entries({ toEqual(1) }, null)

        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nSet = nSet toContain null as Number?
        star = star toContain null as Number?
        nSet = nSet toContain entries(null, { it toEqual 1 }, null)
        star = star toContain entries(null, { it toEqual 1 }, null)
    }
}
