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
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        list = list.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        nList = nList.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        subList = subList.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        star = star.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }

        nList = nList.toContain.inAnyOrder.atLeast(1).entry(null)
        star = star.toContain.inAnyOrder.atLeast(1).entry(null)

        list = list.toContain.inAnyOrder.atLeast(1).entries({}, {})
        nList = nList.toContain.inAnyOrder.atLeast(1).entries({}, {})
        subList = subList.toContain.inAnyOrder.atLeast(1).entries({}, {})
        star = star.toContain.inAnyOrder.atLeast(1).entries({}, {})

        nList = nList.toContain.inAnyOrder.atLeast(1).entries(null, {}, null)
        star = star.toContain.inAnyOrder.atLeast(1).entries(null, {}, null)

        list = list.toContain {}
        nList = nList.toContain {}
        subList = subList.toContain {}
        star = star.toContain {}

        nList = nList.toContain(null)
        star = star.toContain(null)

        list = list.toContain({}, {})
        nList = nList.toContain({}, {})
        subList = subList.toContain({}, {})
        star = star.toContain({}, {})

        nList = nList.toContain(null, {}, null)
        star = star.toContain(null, {}, null)
    }
}
