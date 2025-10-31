package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun2
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableToContainInAnyOrderAtLeast1EntriesExpectationsShortcutTest :
    AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest(
        fun2(Expect<Iterable<Double>>::toContain),
        fun2(Expect<Iterable<Double?>>::toContain).withNullableSuffix(),
    ) {
    @Suppress("AssignedValueIsNeverRead", "unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1, null))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1, null))

        list = list.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        nList = nList.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        subList = subList.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }
        star = star.toContain.inAnyOrder.atLeast(1).entry { toEqual(1) }

        nList = nList.toContain.inAnyOrder.atLeast(1).entry(null)
        star = star.toContain.inAnyOrder.atLeast(1).entry(null)
    }

}
