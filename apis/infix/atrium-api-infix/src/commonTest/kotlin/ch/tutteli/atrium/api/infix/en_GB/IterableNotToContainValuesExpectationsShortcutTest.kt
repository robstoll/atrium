package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableNotToContainValuesExpectationsTest
import kotlin.reflect.KFunction2
import kotlin.test.Test

class IterableNotToContainValuesExpectationsShortcutTest : AbstractIterableNotToContainValuesExpectationsTest(
    getNotToContainShortcutPair(),
    getNotToContainNullablePair(),
    Expect<List<Int>>::notToHaveElementsOrNone.name
) {
    companion object : IterableToContainSpecBase() {

        private fun getNotToContainNullablePair() = notToContain to Companion::notToContainNullableFun

        private fun notToContainNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect notToContain (a)
            else expect notToContain (values(a, *aX))

        private val notToContainShortcutFun: KFunction2<Expect<Iterable<Double>>, Double, Expect<Iterable<Double>>> =
            Expect<Iterable<Double>>::notToContain

        private fun getNotToContainShortcutPair() = notToContainShortcutFun.name to Companion::notToContainShortcut

        private fun notToContainShortcut(expect: Expect<Iterable<Double>>, a: Double, aX: Array<out Double>) =
            if (aX.isEmpty()) expect notToContain (a)
            else expect notToContain (values(a, *aX))
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        list = list notToContain 2
        nList = nList notToContain 2
        subList = subList notToContain 2
        star = star notToContain 2

        list = list notToContain values(2, 1.2)
        nList = nList notToContain values(2, 1.2)
        subList = subList notToContain values(2, 2.2)
        star = star notToContain values(2, 1.2, "asdf")

    }
}
