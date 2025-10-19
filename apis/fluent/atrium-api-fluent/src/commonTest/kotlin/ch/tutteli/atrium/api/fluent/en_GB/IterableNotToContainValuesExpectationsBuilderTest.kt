package ch.tutteli.atrium.api.fluent.en_GB


import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableNotToContainValuesExpectationsTest
import ch.tutteli.atrium.specs.withNullableSuffix
import kotlin.test.Test

class IterableNotToContainValuesExpectationsBuilderTest : AbstractIterableNotToContainValuesExpectationsTest(
    getNotToToContainPair(),
    getNotToContainNullablePair().withNullableSuffix(),
    Expect<List<Int>>::notToHaveElementsOrNone.name,
) {

    companion object : IterableToContainSpecBase() {
        private fun getNotToToContainPair() = "${notToContain}.$atLeast(1).$value/$values" to Companion::notToContainFun

        private fun notToContainFun(
            expect: Expect<Iterable<Double>>,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.notToContain.value(a)
            else expect.notToContain.values(a, *aX)

        private fun getNotToContainNullablePair() =
            "${super.notToContain}.$value/$values" to Companion::notToContainNullableFun

        private fun notToContainNullableFun(
            expect: Expect<Iterable<Double?>>,
            a: Double?,
            aX: Array<out Double?>
        ): Expect<Iterable<Double?>> =
            if (aX.isEmpty()) expect.notToContain.value(a)
            else expect.notToContain.values(a, *aX)
    }

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1))
        var nList: Expect<Set<Number?>> = expect(setOf(1))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf(1))
        var star: Expect<Collection<*>> = expect(listOf(1))


        list = list.notToContain.value(2)
        nList = nList.notToContain.value(2)
        subList = subList.notToContain.value(2)
        star = star.notToContain.value(2)

        list = list.notToContain.values(2, 1.2)
        nList = nList.notToContain.values(2, 1.2)
        subList = subList.notToContain.values(2, 2.2)
        star = star.notToContain.values(2, 1.2, "asdf")

        list = list.notToContain(2, 1.2)
        nList = nList.notToContain(2, 1.2)
        subList = subList.notToContain(2, 2.2)
        // TODO would wish this does not work, maybe @OnlyInputTypes would help?
        subList = subList.notToContain("asdf")
        star = star.notToContain(2, 1.2, "asdf")
    }

}
