package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class IterableToContainInAnyOrderExactlyValuesExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderExactlyValuesExpectationsTest(
        getExactlyTriple(),
        getNotToContainPair()
    ) {

    @Suppress("AssignedValueIsNeverRead", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 1, 1.2, 1.2))
        var nList: Expect<Set<Number?>> = expect(setOf<Number?>(1, 1.2))
        var subList: Expect<ArrayList<Number>> = expect(arrayListOf<Number>(1, 1, 1.2, 1.2))
        var star: Expect<Collection<*>> = expect(listOf(1, 1, 1.2, 1.2, "asdf", "asdf"))

        list = list.toContain.inAnyOrder.exactly(2).value(1)
        nList = nList.toContain.inAnyOrder.exactly(1).value(1)
        subList = subList.toContain.inAnyOrder.exactly(2).value(1)
        star = star.toContain.inAnyOrder.exactly(2).value(1)

        list = list.toContain.inAnyOrder.exactly(2).values(1, 1.2)
        nList = nList.toContain.inAnyOrder.exactly(1).values(1, 1.2)
        subList = subList.toContain.inAnyOrder.exactly(2).values(1, 1.2)
        star = star.toContain.inAnyOrder.exactly(2).values(1, 1.2, "asdf")
    }

    companion object : IterableToContainSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$toContain $what $exactly $times" } to
                ("$toContain.$inAnyOrder.$exactly" to Companion::toContainExactly)

        private fun toContainExactly(
            expect: Expect<Iterable<Double>>,
            exactly: Int,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.toContain.inAnyOrder.exactly(exactly).value(a)
            else expect.toContain.inAnyOrder.exactly(exactly).values(a, *aX)

        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $notToContain instead of $exactly($times)"
    }
}
