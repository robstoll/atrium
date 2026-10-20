package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.specs.integration.AbstractRangeExpectationsTest
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.specs.fun1
import kotlin.test.Test

class RangeExpectationsTest : AbstractRangeExpectationsTest(
    fun1(Expect<Int>::toBeIn),
    fun1(Expect<Long>::toBeIn),
    fun1(Expect<Char>::toBeIn)
){
    @Test
    fun ambiguityTest() {
        val intExpect = expect(10)
        intExpect.toBeIn(1..20)

        val longExpect = expect(100L)
        longExpect.toBeIn(50L..150L)

        val charExpect = expect('f')
        charExpect.toBeIn('a'..'z')
    }
}
