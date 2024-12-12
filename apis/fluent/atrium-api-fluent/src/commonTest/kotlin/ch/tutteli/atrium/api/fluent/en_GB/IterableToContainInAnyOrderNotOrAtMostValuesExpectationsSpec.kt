package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

class IterableToContainInAnyOrderNotOrAtMostValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderNotOrAtMostValuesExpectationsSpec(
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

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

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
