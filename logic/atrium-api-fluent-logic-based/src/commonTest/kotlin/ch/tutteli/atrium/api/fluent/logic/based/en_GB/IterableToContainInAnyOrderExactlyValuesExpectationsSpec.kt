package ch.tutteli.atrium.api.fluent.logic.based.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

class IterableToContainInAnyOrderExactlyValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderExactlyValuesExpectationsSpec(
        getExactlyTriple(),
        getNotToContainPair()
    ) {

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

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list.toContain.inAnyOrder.exactly(2).value(1)
        nList = nList.toContain.inAnyOrder.exactly(2).value(1)
        subList = subList.toContain.inAnyOrder.exactly(2).value(1)
        star = star.toContain.inAnyOrder.exactly(2).value(1)

        list = list.toContain.inAnyOrder.exactly(2).values(1, 1.2)
        nList = nList.toContain.inAnyOrder.exactly(2).values(1, 1.2)
        subList = subList.toContain.inAnyOrder.exactly(2).values(1, 2.2)
        star = star.toContain.inAnyOrder.exactly(2).values(1, 1.2, "asdf")
    }
}
