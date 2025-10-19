package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableNotToContainValuesExpectationsTest
import kotlin.test.Test

class IterableNotToContainValuesExpectationsBuilderTest : AbstractIterableNotToContainValuesExpectationsTest(
    getNotToContainPair(),
    getNotToContainNullablePair(),
    Expect<List<Int>>::notToHaveElementsOrNone.name
) {

    companion object : IterableToContainSpecBase() {

        private fun getNotToContainPair() = notToContain to Companion::notToContainFun

        private fun notToContainFun(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect notToContain o value a
            else expect notToContain o the values(a, *aX)

        private fun getNotToContainNullablePair() = notToContain to Companion::notToContainNullableFun

        private fun notToContainNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect notToContain o value a
            else expect notToContain o the values(a, *aX)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))

        list = list notToContain o value 2
        nList = nList notToContain o value 2
        subList = subList notToContain o value 2
        star = star notToContain o value 2

        list = list notToContain o the values(2, 1.2)
        nList = nList notToContain o the values(2, 1.2)
        subList = subList notToContain o the values(2, 2.2)
        star = star notToContain o the values(2, 1.2, "asdf")
    }
}
