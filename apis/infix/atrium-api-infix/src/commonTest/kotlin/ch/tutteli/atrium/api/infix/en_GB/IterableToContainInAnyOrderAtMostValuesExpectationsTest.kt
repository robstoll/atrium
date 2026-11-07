package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.notImplemented

class IterableToContainInAnyOrderAtMostValuesExpectationsSpec :
    ch.tutteli.atrium.specs.integration.IterableToContainInAnyOrderAtMostValuesExpectationsSpec(
        getAtMostTriple(),
        getNotToContainPair(),
        getExactlyPair()
    ) {

    companion object : IterableToContainSpecBase() {

        private fun getAtMostTriple() =
            { what: String, times: String -> "$toContain $what $atMost $times" } to
                ("$toContain $filler $inAnyOrder $atMost" to Companion::toContainAtMost)

        private fun toContainAtMost(expect: Expect<Iterable<Double>>, atMost: Int, a: Double, aX: Array<out Double>) =
            if(aX.isEmpty()) expect toContain o inAny order atMost atMost value a
            else expect toContain o inAny order atMost atMost the values(a, *aX)


        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContain` instead of `$atMost $times`"

        private fun getExactlyPair() = exactly to Companion::getErrorMsgExactly

        private fun getErrorMsgExactly(times: Int) =
            "use `$exactly $times` instead of `$atMost $times`; `$atMost $times` defines implicitly `$atLeast $times` as well"
    }

    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inAny order atMost 2 value(1)
        nList = nList toContain o inAny order atMost 2 value(1)
        subList = subList toContain o inAny order atMost 2 value(1)
        star = star toContain o inAny order atMost 2 value(1)

        //TODO type parameter should not be necessary, check with Kotlin 1.4
        list = list toContain o inAny order atMost 2 the values<Number>(1, 1.2)
        nList = nList toContain o inAny order atMost 2 the values<Number>(1, 1.2)
        subList = subList toContain o inAny order atMost 2 the values<Number>(1, 2.2)
        star = star toContain o inAny order atMost 2 the values(1, 1.2, "asdf")
    }
}
