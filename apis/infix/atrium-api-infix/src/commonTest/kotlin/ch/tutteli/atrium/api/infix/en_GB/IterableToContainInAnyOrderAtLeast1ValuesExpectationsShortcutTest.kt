package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest
import kotlin.reflect.KFunction2
import kotlin.test.Test

class IterableToContainInAnyOrderAtLeast1ValuesExpectationsShortcutTest :
    AbstractIterableToContainInAnyOrderAtLeast1ValuesExpectationsTest(
        getToContainShortcutPair(),
        getToContainNullableShortcutPair()
    ) {
    companion object : IterableToContainSpecBase() {

        private val toContainFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::toContain

        fun getToContainShortcutPair() = toContainFun.name to Companion::toContainValuesShortcut

        private fun toContainValuesShortcut(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain a
            else expect toContain values(a, *aX)


        private val toContainNullableFun: KFunction2<Expect<Iterable<Double?>>, Double?, Expect<Iterable<Double?>>> =
            Expect<Iterable<Double?>>::toContain

        fun getToContainNullableShortcutPair() = toContainNullableFun.name to Companion::toContainNullableValuesShortcut

        private fun toContainNullableValuesShortcut(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect toContain a
            else expect toContain values(a, *aX)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1.2))
        var nSet: Expect<Set<Number?>> = expect(setOf(1, null, 1.2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1, 1.2, 2.2))
        var star: Expect<Collection<*>> = expect(listOf(1, null, 1.2, "asdf"))

        list = list toContain 1
        nSet = nSet toContain 1
        subList = subList toContain 1
        star = star toContain 1

        list = list toContain values(1, 1.2)
        nSet = nSet toContain values(1, 1.2)
        subList = subList toContain values(1, 2.2)
        star = star toContain values(1, 1.2, "asdf")
    }
}
