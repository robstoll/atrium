package ch.tutteli.atrium.api.infix.en_GB

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
                ("$toContain $filler $inAnyOrder $exactly" to Companion::containsExactly)

        private fun containsExactly(
            expect: Expect<Iterable<Double>>,
            exactly: Int,
            a: Double,
            aX: Array<out Double>
        ): Expect<Iterable<Double>> =
            if (aX.isEmpty()) expect toContain o inAny order exactly exactly value a
            else expect toContain o inAny order exactly exactly the values(a, *aX)

        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContain` instead of `$exactly $times`"
    }


    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inAny order exactly 2 value 1
        nList = nList toContain o inAny order exactly 2 value 1
        subList = subList toContain o inAny order exactly 2 value 1
        star = star toContain o inAny order exactly 2 value 1

        //TODO type parameter should not be necessary, check with Kotlin 1.4
        list = list toContain o inAny order exactly 2 the values<Number>(1, 1.2)
        nList = nList toContain o inAny order exactly 2 the values<Number>(1, 1.2)
        subList = subList toContain o inAny order exactly 2 the values<Number>(1, 2.2)
        star = star toContain o inAny order exactly 2 the values(1, 1.2, "asdf")
    }
}
