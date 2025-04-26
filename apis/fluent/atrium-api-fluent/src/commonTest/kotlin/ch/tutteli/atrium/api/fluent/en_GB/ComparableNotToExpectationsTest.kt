package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.NOT_TO_BE_GREATER_THAN
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionComparableProof.NOT_TO_BE_LESS_THAN
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractComparableExpectationsTest
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo
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

    NOT_TO_BE_GREATER_THAN,
    NOT_TO_BE_LESS_THAN,
) {
    @Test
    fun trigger_run_gutter() = 1
}
