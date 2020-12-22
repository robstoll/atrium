package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

class IterableContainsInAnyOrderExactlyValuesAssertionsSpec :
    ch.tutteli.atrium.specs.integration.IterableContainsInAnyOrderExactlyValuesAssertionsSpec(
        getExactlyTriple(),
        getContainsNotPair(),
        "◆ "
    ) {

    companion object : IterableContainsSpecBase() {

        private fun getExactlyTriple() =
            { what: String, times: String -> "$contains $what $exactly $times" } to
                ("$contains.$inAnyOrder.$exactly" to Companion::containsExactly)

        private fun containsExactly(
            expect: Expect<Iterable<Double>>,
            exactly: Int,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect.contains.inAnyOrder.exactly(exactly).value(a)
            else expect.contains.inAnyOrder.exactly(exactly).values(a, *aX)

        private fun getContainsNotPair() = containsNot to Companion::getErrorMsgContainsNot

        private fun getErrorMsgContainsNot(times: Int) = "use $containsNot instead of $exactly($times)"
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.contains.inAnyOrder.exactly(2).value(1)
        nList = nList.contains.inAnyOrder.exactly(2).value(1)
        subList = subList.contains.inAnyOrder.exactly(2).value(1)
        star = star.contains.inAnyOrder.exactly(2).value(1)

        list = list.contains.inAnyOrder.exactly(2).values(1, 1.2)
        nList = nList.contains.inAnyOrder.exactly(2).values(1, 1.2)
        subList = subList.contains.inAnyOrder.exactly(2).values(1, 2.2)
        star = star.contains.inAnyOrder.exactly(2).values(1, 1.2, "asdf")
    }

}
