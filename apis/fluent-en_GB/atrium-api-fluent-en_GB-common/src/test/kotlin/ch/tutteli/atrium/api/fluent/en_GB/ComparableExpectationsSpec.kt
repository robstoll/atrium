package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import org.spekframework.spek2.Spek

object ComparableExpectationsSpec : Spek({

    include(object : ch.tutteli.atrium.specs.integration.ComparableExpectationsSpec(
        fun1(Expect<Int>::toBeLessThan),
        fun1(Expect<Int>::toBeLessThanOrEqualTo),
        fun1(Expect<Int>::toBeEqualComparingTo),
        fun1(Expect<Int>::toBeGreaterThanOrEqualTo),
        fun1(Expect<Int>::toBeGreaterThan),

        fun1(Expect<DiffEqualsCompareTo>::toBeLessThanOrEqualTo),
        fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo),
        fun1(Expect<DiffEqualsCompareTo>::toBeGreaterThanOrEqualTo),
        describePrefix = "[Atrium][<=] "
    ) {})

    include(object : ch.tutteli.atrium.specs.integration.ComparableExpectationsSpec(
        fun1(Expect<Int>::toBeLessThan),
        fun1(Expect<Int>::notToBeGreaterThan),
        fun1(Expect<Int>::toBeEqualComparingTo),
        fun1(Expect<Int>::notToBeLessThan),
        fun1(Expect<Int>::toBeGreaterThan),

        fun1(Expect<DiffEqualsCompareTo>::notToBeGreaterThan),
        fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo),
        fun1(Expect<DiffEqualsCompareTo>::notToBeLessThan),

        DescriptionComparableAssertion.IS_NOT_GREATER_THAN.getDefault(),
        DescriptionComparableAssertion.IS_NOT_LESS_THAN.getDefault(),
        describePrefix = "[Atrium][!>] "
    ) {})

})
