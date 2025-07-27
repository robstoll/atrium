package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.specs.integration.AbstractRangeExpectationsTest
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.fun1
import kotlin.test.Test

class RangeExpectationsTest : AbstractRangeExpectationsTest(
    fun1(Expect<Int>::toBeInRange),
    fun1(Expect<Long>::toBeInRange),
    fun1(Expect<Char>::toBeInRange)
){
    @Test
    fun ambiguityTest() {
        val intExpect = expect(10)
        intExpect.toBeInRange(1..20)

        val longExpect = expect(100L)
        longExpect.toBeInRange(50L..150L)

        val charExpect = expect('f')
        charExpect.toBeInRange('a'..'z')
    }
}
