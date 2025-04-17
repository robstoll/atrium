package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractComparableExpectationsTest
import ch.tutteli.atrium.specs.integration.DiffEqualsCompareTo
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_GREATER_THAN_OR_EQUAL_TO
import ch.tutteli.atrium.translations.DescriptionComparableExpectation.TO_BE_LESS_THAN_OR_EQUAL_TO

class ComparableExpectationsTest : AbstractComparableExpectationsTest(
    fun1(Expect<Int>::toBeLessThan),
    fun1(Expect<Int>::toBeLessThanOrEqualTo),
    fun1(Expect<Int>::toBeEqualComparingTo),
    fun1(Expect<Int>::toBeGreaterThanOrEqualTo),
    fun1(Expect<Int>::toBeGreaterThan),

    fun1(Expect<DiffEqualsCompareTo>::toBeLessThanOrEqualTo),
    fun1(Expect<DiffEqualsCompareTo>::toBeEqualComparingTo),
    fun1(Expect<DiffEqualsCompareTo>::toBeGreaterThanOrEqualTo),

    TO_BE_LESS_THAN_OR_EQUAL_TO.getDefault(),
    TO_BE_GREATER_THAN_OR_EQUAL_TO.getDefault(),
)
