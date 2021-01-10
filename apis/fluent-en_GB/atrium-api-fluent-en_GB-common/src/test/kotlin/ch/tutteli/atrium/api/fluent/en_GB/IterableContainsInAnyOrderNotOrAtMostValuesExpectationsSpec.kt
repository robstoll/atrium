package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

class IterableContainsInAnyOrderNotOrAtMostValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderNotOrAtMostValuesExpectationsSpec(
        getNotOrAtMostTriple(),
        getContainsNotPair(),
        "◆ "
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getNotOrAtMostTriple() =
            { what: String, times: String -> "$contains $what $notOrAtMost $times" } to
                ("$contains.$notOrAtMost" to Companion::containsNotOrAtMost)

        private fun containsNotOrAtMost(
            expect: Expect<Iterable<Double>>,
            atMost: Int,
            a: Double,
            aX: Array<out Double>
        ) = expect.contains.inAnyOrder.notOrAtMost(atMost).values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $notOrAtMost($times)"
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inAnyOrder.notOrAtMost(1).value(1)
        nList = nList.contains.inAnyOrder.notOrAtMost(1).value(1)
        subList = subList.contains.inAnyOrder.notOrAtMost(1).value(1)
        star = star.contains.inAnyOrder.notOrAtMost(1).value(1)

        list = list.contains.inAnyOrder.notOrAtMost(1).values(1, 1.2)
        nList = nList.contains.inAnyOrder.notOrAtMost(1).values(1, 1.2)
        subList = subList.contains.inAnyOrder.notOrAtMost(1).values(1, 2.2)
        star = star.contains.inAnyOrder.notOrAtMost(1).values(1, 1.2, "asdf")
    }
}
