package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.integration.AbstractIterableToContainInAnyOrderAtMostValuesExpectationsTest
import kotlin.test.Test

class IterableToContainInAnyOrderAtMostValuesExpectationsTest :
    AbstractIterableToContainInAnyOrderAtMostValuesExpectationsTest(
        getAtMostTriple(),
        getNotToContainPair(),
        getExactlyPair()
    ) {

    companion object : IterableToContainSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain.$inAnyOrder.$atMost" to Companion::toContainAtMost)

        private fun toContainAtMost(expect: Expect<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>) =
            expect.toContain.inAnyOrder.atMost(atMost).values(a, *aX)


        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use $notToContain instead of $atMost($times)"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use $exactly($times) instead of $atMost($times); $atMost($times) defines implicitly $atLeast($times) as well"
    }

    @Suppress("unused", "UNUSED_VALUE")
    @Test
    fun ambiguityTest() {
        var list: Expect<List<Number>> = expect(listOf<Number>(1, 1.2, 2))
        var nList: Expect<Set<Number?>> = expect(setOf<Number?>(1, 1.2, null))
        var subList: Expect<ArrayList<Number>> = expect(ArrayList(listOf<Number>(1, 2.2)))
        var star: Expect<Collection<*>> = expect(listOf(1, 1.2, "asdf"))

        list = list.toContain.inAnyOrder.atMost(2).value(1)
        nList = nList.toContain.inAnyOrder.atMost(2).value(1)
        subList = subList.toContain.inAnyOrder.atMost(2).value(1)
        star = star.toContain.inAnyOrder.atMost(2).value(1)

        list = list.toContain.inAnyOrder.atMost(2).values(1, 1.2)
        nList = nList.toContain.inAnyOrder.atMost(2).values(1, 1.2)
        subList = subList.toContain.inAnyOrder.atMost(2).values(1, 2.2)
        star = star.toContain.inAnyOrder.atMost(2).values(1, 1.2, "asdf")
    }
}
