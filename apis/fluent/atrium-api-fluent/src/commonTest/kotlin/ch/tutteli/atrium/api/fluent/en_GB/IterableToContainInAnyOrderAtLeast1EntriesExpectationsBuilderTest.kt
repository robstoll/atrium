package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest
import kotlin.test.Test

class IterableToContainInAnyOrderAtLeast1EntriesExpectationsBuilderTest :
    AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest(
        functionDescription to Companion::toContainInAnyOrderEntries,
        functionDescription to Companion::toContainNullableEntries
    ) {

    companion object : IterableToContainSpecBase() {
        val functionDescription = "$toContain.$inAnyOrder.$atLeast(1).$entry/$entries"


        private fun toContainInAnyOrderEntries(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.atLeast(1).entry(a)
            else expect.toContain.inAnyOrder.atLeast(1).entries(a, *aX)

        private fun toContainNullableEntries(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.atLeast(1).entry(a)
            else expect.toContain.inAnyOrder.atLeast(1).entries(a, *aX)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, null))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1, null))

        list = list.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        nSet = nSet.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        subList = subList.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        star = star.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }

        nSet = nSet.toContain.inAnyOrder.atLeast(1).entry(null)
        star = star.toContain.inAnyOrder.atLeast(1).entry(null)

        list = list.toContain.inAnyOrder.atLeast(1).entries({ toEqual(1) }, { notToEqual(2) })
        nSet = nSet.toContain.inAnyOrder.atLeast(1).entries({ toEqual(1) }, null)
        subList = subList.toContain.inAnyOrder.atLeast(1).entries({ toEqual(1) }, { notToEqual(2) })
        star = star.toContain.inAnyOrder.atLeast(1).entries({ toEqual(1) }, null)

        nSet = nSet.toContain.inAnyOrder.atLeast(1).entries(null, { toEqual(1) }, null)
        star = star.toContain.inAnyOrder.atLeast(1).entries(null, { toEqual(1) }, null)
    }
}
