package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class IterableToContainInAnyOrderNotOrAtMostValuesExpectationsTest :
    ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderNotOrAtMostValuesExpectationsTest(
        getNotOrAtMostTriple(),
        getNotToContainPair()
    ) {

    companion object : IterableToContainSpecBase() {

        private fun getNotOrAtMostTriple() =
            { what: String, times: String -> "$toContain $what $notOrAtMost $times" } to
                ("$toContain.$notOrAtMost" to Companion::toContainNotOrAtMost)

        private fun toContainNotOrAtMost(
            expect: Expect<Iterable<Double>>,
            atMost: Int,
            a: Double,
            aX: Array<out Double>
        ) = expect.toContain.inAnyOrder.notOrAtMost(atMost).values(a, *aX)

        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $notToContain instead of $notOrAtMost($times)"
    }

    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf(1, 2))
        var nList: Expect<Set<Number?>> = expect(setOf(1, null))
        var subList: Expect<ArrayList<Number>> = expect(ArrayList(listOf(1, 2)))
        var star: Expect<Collection<*>> = expect(listOf(1, "two"))

        list = list.toContain.inAnyOrder.notOrAtMost(1).value(1)
        nList = nList.toContain.inAnyOrder.notOrAtMost(1).value(1)
        subList = subList.toContain.inAnyOrder.notOrAtMost(1).value(1)
        star = star.toContain.inAnyOrder.notOrAtMost(1).value(1)

        list = list.toContain.inAnyOrder.notOrAtMost(1).values(1, 1.2)
        nList = nList.toContain.inAnyOrder.notOrAtMost(1).values(1, 1.2)
        subList = subList.toContain.inAnyOrder.notOrAtMost(1).values(1, 2.2)
        star = star.toContain.inAnyOrder.notOrAtMost(1).values(1, 1.2, "asdf")
    }
}
