package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

class IterableContainsInAnyOrderAtMostValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderAtMostValuesExpectationsSpec(
        getAtMostTriple(),
        getContainsNotPair(),
        getExactlyPair(),
        "◆ "
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$contains $what $atMost $times" } to
                ("$contains.$inAnyOrder.$atMost" to Companion::containsAtMost)

        private fun containsAtMost(expect: Expect<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>) =
            expect.contains.inAnyOrder.atMost(atMost).values(a, *aX)


        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $atMost($times)"

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

        list = list.contains.inAnyOrder.atMost(2).value(1)
        nList = nList.contains.inAnyOrder.atMost(2).value(1)
        subList = subList.contains.inAnyOrder.atMost(2).value(1)
        star = star.contains.inAnyOrder.atMost(2).value(1)

        list = list.contains.inAnyOrder.atMost(2).values(1, 1.2)
        nList = nList.contains.inAnyOrder.atMost(2).values(1, 1.2)
        subList = subList.contains.inAnyOrder.atMost(2).values(1, 2.2)
        star = star.contains.inAnyOrder.atMost(2).values(1, 1.2, "asdf")
    }
}
