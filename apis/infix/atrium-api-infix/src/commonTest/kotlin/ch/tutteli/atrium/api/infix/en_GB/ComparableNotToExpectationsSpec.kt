package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractComparableExpectationsTest
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo
import ch.tutteli.atrium.translations.DescriptionComparableExpectation
import kotlin.test.Test

class ComparableNotToExpectationsTest : AbstractComparableExpectationsTest(
    fun1(Expect<Int>::toBeLessThan),
    fun1(Expect<Int>::notToBeGreaterThan),
    fun1(Expect<Int>::toBeEqualComparingTo),
    fun1(Expect<Int>::notToBeLessThan),
    fun1(Expect<Int>::toBeGreaterThan),

    fun1(Expect<DiffEqualsCompareTo>::notToBeGreaterThan),
    fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo),
    fun1(Expect<DiffEqualsCompareTo>::notToBeLessThan),

    DescriptionComparableExpectation.NOT_TO_BE_GREATER_THAN.getDefault(),
    DescriptionComparableExpectation.NOT_TO_BE_LESS_THAN.getDefault()
) {
    @Test
    fun ambiguityTest() {
        val a1: Expect<Int> = expect(1)
        a1 toBeLessThan 2
        a1 notToBeGreaterThan 1
        a1 toBeGreaterThan 0
        a1 notToBeLessThan 1
        a1 toBeEqualComparingTo 1
    }
}
