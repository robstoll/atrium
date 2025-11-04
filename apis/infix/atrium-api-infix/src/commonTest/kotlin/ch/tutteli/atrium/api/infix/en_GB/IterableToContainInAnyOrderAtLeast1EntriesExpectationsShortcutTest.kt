package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest
import kotlin.reflect.KFunction2
import kotlin.test.Test

class IterableToContainInAnyOrderAtLeast1EntriesExpectationsShortcutTest :
    AbstractIterableToContainInAnyOrderAtLeast1EntriesExpectationsTest(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair()
    ) {
    companion object : IterableToContainSpecBase() {
        private val toContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Expect<Double>.() -> Unit, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContain

        fun getToContainShortcutPair() = toContainShortcutFun.name to Companion::toContainInAnyOrderEntriesShortcut

        private fun toContainInAnyOrderEntriesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Expect<Double>.() -> Unit,
            aX: Array<out Expect<Double>.() -> Unit>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain (a)
            else expect toContain entries(a, *aX)

        private val toContainEntriesFun: KFunction2<Expect<Iterable<Double?>>, (Expect<Double>.() -> Unit)?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContain

        fun getToContainNullableShortcutPair() = toContainEntriesFun.name to Companion::toContainNullableEntriesShortcut

        private fun toContainNullableEntriesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: (Expect<Double>.() -> Unit)?,
            aX: Array<out (Expect<Double>.() -> Unit)?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain (a)
            else expect toContain (entries(a, *aX))
    }

    @Suppress("AssignedValueIsNeverRead", "unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, null))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1, null))

        list = list toContain { toEqual(1) }
        nSet = nSet toContain { toEqual(1) }
        subList = subList toContain { toEqual(1) }
        star = star toContain { toEqual(1) }

        nSet = nSet toContain (null)
        star = star toContain (null)

        list = list toContain entries({ toEqual(1) }, { notToEqual(2) })
        nSet = nSet toContain entries({ toEqual(1) }, null)
        subList = subList toContain entries({ toEqual(1) }, { notToEqual(2) })
        star = star toContain entries({ toEqual(1) }, null)

        //TODO should work without cast, remove as soon as KT-6591 is fixed - (with Kotlin 1.4)
        nSet = nSet toContain null as Number?
        star = star toContain null as Number?
        nSet = nSet toContain entries(null, { toEqual(1) }, null)
        star = star toContain entries(null, { toEqual(1) }, null)
    }
}
