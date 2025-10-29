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
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        list = list toContain o inAny order atLeast 1 entry {
            it toEqual 1
        }
        nList = nList toContain o inAny order atLeast 1 entry {
            it toEqual 1
        }
        subList = subList toContain o inAny order atLeast 1 entry {
            it toEqual 1
        }
        star = star toContain o inAny order atLeast 1 entry {
            it toEqual 1
        }

        nList = nList toContain o inAny order atLeast 1 entry null
        star = star toContain o inAny order atLeast 1 entry null

        list = list toContain o inAny order atLeast 1 the entries({}, {})
        nList = nList toContain o inAny order atLeast 1 the entries({}, {})
        subList = subList toContain o inAny order atLeast 1 the entries({}, {})
        star = star toContain o inAny order atLeast 1 the entries({}, {})

        nList = nList toContain o inAny order atLeast 1 the entries(null, {}, null)
        star = star toContain o inAny order atLeast 1 the entries(null, {}, null)

        list = list toContain {}
        nList = nList toContain {}
        subList = subList toContain {}
        star = star toContain {}

        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nList = nList toContain null as Number?
        star = star toContain null as Number?

        list = list toContain entries({}, {})
        nList = nList toContain entries({}, {})
        subList = subList toContain entries({}, {})
        star = star toContain entries({}, {})

        nList = nList toContain entries(null, {}, null)
        star = star toContain entries(null, {}, null)
    }
}
