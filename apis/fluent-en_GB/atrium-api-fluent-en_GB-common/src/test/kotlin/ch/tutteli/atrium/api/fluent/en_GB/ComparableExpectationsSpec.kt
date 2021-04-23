package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo

object ComparableExpectationsSpec : ch.tutteli.atrium.specs.integration.ComparableExpectationsSpec(
    fun1(Expect<Int>::toBeLessThan),
    fun1(Expect<Int>::toBeLessThanOrEqualTo),
    fun1(Expect<Int>::toBeGreaterThan),
    fun1(Expect<Int>::toBeGreaterThanOrEqualTo),
    fun1(Expect<Int>::toBeEqualComparingTo),
    fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo)
)
