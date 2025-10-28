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
        var nSet: Expect<Set<Number?>> = expect(setOf(1, null))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1, null))

        list = list.toContain { toEqual(1) }
        nSet = nSet.toContain { toEqual(1) }
        subList = subList.toContain { toEqual(1) }
        star = star.toContain { toEqual(1) }

        nSet = nSet.toContain(null)
        star = star.toContain(null)

        list = list.toContain({ toEqual(1) }, { notToEqual(2) })
        nSet = nSet.toContain({ toEqual(1) }, null)
        subList = subList.toContain({ toEqual(1) }, { notToEqual(2) })
        star = star.toContain({ toEqual(1) }, null)

        nSet = nSet.toContain(null, { toEqual(1) }, null)
        star = star.toContain(null, { toEqual(1) }, null)


    }

}
