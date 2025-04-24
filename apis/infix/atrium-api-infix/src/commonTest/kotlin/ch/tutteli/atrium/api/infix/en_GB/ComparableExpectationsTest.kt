package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.TO_BE_GREATER_THAN_OR_EQUAL_TO
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.TO_BE_LESS_THAN_OR_EQUAL_TO
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractComparableExpectationsTest
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo
import kotlin.test.Test

class ComparableExpectationsTest : AbstractComparableExpectationsTest(
    fun1(Expect<Int>::toBeLessThan),
    fun1(Expect<Int>::toBeLessThanOrEqualTo),
    fun1(Expect<Int>::toBeEqualComparingTo),
    fun1(Expect<Int>::toBeGreaterThanOrEqualTo),
    fun1(Expect<Int>::toBeGreaterThan),

    fun1(Expect<DiffEqualsCompareTo>::toBeLessThanOrEqualTo),
    fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo),
    fun1(Expect<DiffEqualsCompareTo>::toBeGreaterThanOrEqualTo),

    TO_BE_LESS_THAN_OR_EQUAL_TO,
    TO_BE_GREATER_THAN_OR_EQUAL_TO,
) {
    @Test
    fun ambiguityTest() {
        val a1: Expect<Int> = expect(1)
        a1 toBeLessThan 2
        a1 toBeLessThanOrEqualTo 1
        a1 toBeGreaterThan 0
        a1 toBeGreaterThanOrEqualTo 1
        a1 toBeEqualComparingTo 1
    }
}
