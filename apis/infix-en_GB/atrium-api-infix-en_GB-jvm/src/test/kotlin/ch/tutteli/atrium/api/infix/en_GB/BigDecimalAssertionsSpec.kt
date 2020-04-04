package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.BigDecimalAssertionsSpec
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

class BigDecimalAssertionsSpec : Spek({
    include(object : BigDecimalAssertionsSpec(
        fun1(Companion::toBeBigDecimal),
        fun1(Companion::toBeNullable),
        fun1(Companion::toBeNull),
        Expect<Any>::toBe,
        fun1(Companion::notToBe),
        Expect<Any>::notToBe,
        fun1(Companion::isNumericallyEqualTo),
        fun1(Companion::isNotNumericallyEqualTo),
        fun1(Companion::isEqualIncludingScale),
        fun1(Companion::isNotEqualIncludingScale)
    ) {})

    describe("fun toBe for BigDecimal? and subject is null") {
        it("chooses the correct overload if `null` is passed, does not throw") {
            expect(null as BigDecimal?) toBe null
        }
    }
}) {
    companion object {
        @Suppress("DEPRECATION")
        fun toBeBigDecimal(expect: Expect<BigDecimal>, a: BigDecimal): Nothing = expect toBe a

        @Suppress("DEPRECATION")
        fun toBeNullable(expect: Expect<BigDecimal?>, a: BigDecimal?): Nothing = expect toBe a

        fun toBeNull(expect: Expect<BigDecimal?>, nothing: Nothing?) = expect toBe nothing

        @Suppress("DEPRECATION")
        fun notToBe(expect: Expect<BigDecimal>, a: BigDecimal): Nothing = expect notToBe a

        fun isNumericallyEqualTo(expect: Expect<BigDecimal>, a: BigDecimal) = expect isNumericallyEqualTo a

        fun isNotNumericallyEqualTo(expect: Expect<BigDecimal>, a: BigDecimal) = expect isNotNumericallyEqualTo a

        fun isEqualIncludingScale(expect: Expect<BigDecimal>, a: BigDecimal) = expect isEqualIncludingScale a

        fun isNotEqualIncludingScale(expect: Expect<BigDecimal>, a: BigDecimal) = expect isNotEqualIncludingScale a
    }
}
