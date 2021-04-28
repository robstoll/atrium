package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

class IterableContainsInAnyOrderAtMostValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtMostValuesExpectationsSpec(
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
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

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
