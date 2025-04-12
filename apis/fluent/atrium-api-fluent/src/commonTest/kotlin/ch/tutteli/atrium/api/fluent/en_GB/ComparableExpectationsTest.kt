package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.AbstractComparableExpectationsTest
import kotlin.test.Test

class ComparableExpectationsTest : AbstractComparableExpectationsTest(
    fun1(Expect<Int>::toBeLessThan),
    fun1(Expect<Int>::toBeLessThanOrEqualTo),
    fun1(Expect<Int>::toBeEqualComparingTo),
    fun1(Expect<Int>::toBeGreaterThanOrEqualTo),
    fun1(Expect<Int>::toBeGreaterThan),
) {
    @Test
    fun foo() {
        expect(1).toEqual(2)
    }
}
