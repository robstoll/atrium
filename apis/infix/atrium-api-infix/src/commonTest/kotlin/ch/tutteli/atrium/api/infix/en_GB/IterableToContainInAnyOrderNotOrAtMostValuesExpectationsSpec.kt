package ch.tutteli.atrium.api.infix.en_GB

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
                ("$toContain $filler $notOrAtMost" to Companion::notToContainOrAtMost)

        private fun notToContainOrAtMost(
            expect: Expect<Iterable<Double>>,
            atMost: Int,
            a: Double,
            aX: Array<out Double>
        ) =
            if (aX.isEmpty()) expect toContain o inAny order notOrAtMost atMost value a
            else expect toContain o inAny order notOrAtMost atMost the values(a, *aX)

        private fun getNotToContainPair() = notToContain to Companion::getErrorMsgNotToContain

        private fun getErrorMsgNotToContain(times: Int) = "use `$notToContain` instead of `$notOrAtMost $times`"
    }

    @Suppress("unused")
    private fun ambiguityTest() {
        var list: Expect<List<Number>> = notImplemented()
        var nList: Expect<Set<Number?>> = notImplemented()
        var subList: Expect<ArrayList<Number>> = notImplemented()
        var star: Expect<Collection<*>> = notImplemented()

        list = list toContain o inAny order notOrAtMost 1 value 1
        nList = nList toContain o inAny order notOrAtMost 1 value 1
        subList = subList toContain o inAny order notOrAtMost 1 value 1
        star = star toContain o inAny order notOrAtMost 1 value 1

        //TODO type parameter should not be necessary, check with Kotlin 1.4
        list = list toContain o inAny order notOrAtMost 1 the values<Number>(1, 1.2)
        nList = nList toContain o inAny order notOrAtMost 1 the values<Number>(1, 1.2)
        subList = subList toContain o inAny order notOrAtMost 1 the values<Number>(1, 2.2)
        star = star toContain o inAny order notOrAtMost 1 the values(1, 1.2, "asdf")
    }
}
