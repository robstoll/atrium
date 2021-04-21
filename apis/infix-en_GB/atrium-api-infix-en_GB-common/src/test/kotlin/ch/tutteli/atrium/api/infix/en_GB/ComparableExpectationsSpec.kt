package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo
import ch.tutteli.atrium.specs.notImplemented

class ComparableExpectationsSpec : ch.tutteli.atrium.specs.integration.ComparableExpectationsSpec(
    fun1(Expect<Int>::toBeLessThan),
    fun1(Expect<Int>::toBeLessThanOrEqual),
    fun1(Expect<Int>::toBeGreaterThan),
    fun1(Expect<Int>::toBeGreaterThanOrEqual),
    fun1(Expect<Int>::toBeEqualComparingTo),
    fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo)
) {

    @Suppress("unused")
    fun ambiguityTest() {
        val a1: Expect<Int> = notImplemented()
        a1 toBeLessThan 1
        a1 toBeLessThanOrEqual 1
        a1 toBeGreaterThan 1
        a1 toBeGreaterThanOrEqual 1
        a1 toBeEqualComparingTo 1
    }
}
