package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo
import ch.tutteli.atrium.specs.notImplemented
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import org.spekframework.spek2.Spek

class ComparableExpectationsSpec: Spek({

    include(object: ch.tutteli.atrium.specs.integration.ComparableExpectationsSpec(
        fun1(Expect<Int>::toBeLessThan),
        fun1(Expect<Int>::toBeLessThanOrEqualTo),
        fun1(Expect<Int>::toBeEqualComparingTo),
        fun1(Expect<Int>::toBeGreaterThanOrEqualTo),
        fun1(Expect<Int>::toBeGreaterThan),

        fun1(Expect<DiffEqualsCompareTo>::toBeLessThanOrEqualTo),
        fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo),
        fun1(Expect<DiffEqualsCompareTo>::toBeGreaterThanOrEqualTo),
        describePrefix = "[Atrium][<=] "
    ){})

    include(object: ch.tutteli.atrium.specs.integration.ComparableExpectationsSpec(
        fun1(Expect<Int>::toBeLessThan),
        fun1(Expect<Int>::notToBeGreaterThan),
        fun1(Expect<Int>::toBeEqualComparingTo),
        fun1(Expect<Int>::notToBeLessThan),
        fun1(Expect<Int>::toBeGreaterThan),

        fun1(Expect<DiffEqualsCompareTo>::notToBeGreaterThan),
        fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo),
        fun1(Expect<DiffEqualsCompareTo>::notToBeLessThan),

        DescriptionComparableExpectation.NOT_TO_BE_GREATER_THAN.getDefault(),
        DescriptionComparableExpectation.NOT_TO_BE_LESS_THAN.getDefault(),
        describePrefix = "[Atrium][!>] "
    ){})
})  {

    @Suppress("unused")
    fun ambiguityTest() {
        val a1: Expect<Int> = notImplemented()
        a1 toBeLessThan 1
        a1 toBeLessThanOrEqualTo 1
        a1 toBeGreaterThan 1
        a1 toBeGreaterThanOrEqualTo 1
        a1 toBeEqualComparingTo 1
    }
}
