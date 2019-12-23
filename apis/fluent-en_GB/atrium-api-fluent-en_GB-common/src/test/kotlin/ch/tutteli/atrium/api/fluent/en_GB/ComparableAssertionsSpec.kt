package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.notImplemented

object ComparableAssertionsSpec : ch.tutteli.atrium.specs.integration.ComparableAssertionsSpec(
    fun1(Expect<Int>::isLessThan),
    fun1(Expect<Int>::isLessOrEquals),
    fun1(Expect<Int>::isGreaterThan),
    fun1(Expect<Int>::isGreaterOrEquals)
){
    @Suppress("unused", "UNUSED_VALUE")
    private fun ambiguityTest() {
        var a1: Expect<Int> = notImplemented()

        a1 = a1.isLessThan(1)
        a1 = a1.isLessOrEquals(1)
        a1 = a1.isGreaterThan(1)
        a1 = a1.isGreaterOrEquals(1)

        // neither Expect<out Int> nor Int? is comparable, hence no a2 and also no a1b, a2b etc.
    }
}

