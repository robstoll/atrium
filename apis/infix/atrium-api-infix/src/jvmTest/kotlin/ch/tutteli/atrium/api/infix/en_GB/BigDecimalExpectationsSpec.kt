package ch.tutteli.atrium.api.infix.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.fun1
import ch.tutteli.atrium.specs.integration.BigDecimalExpectationsSpec
import ch.tutteli.atrium.specs.withNullableSuffix
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.math.BigDecimal

class BigDecimalExpectationsSpec : Spek({
    include(object : BigDecimalExpectationsSpec(
        fun1(Companion::toEqualBigDecimal),
        fun1(Companion::toEqualNullable).withNullableSuffix(),
        fun1(Companion::toEqualNull).withNullableSuffix(),
        Expect<Any>::toEqual,
        fun1(Companion::notToEqual),
        Expect<Any>::notToEqual,
        fun1(Companion::toEqualNumerically),
        fun1(Companion::notToEqualNumerically),
        fun1(Companion::toEqualIncludingScale),
        fun1(Companion::notToEqualIncludingScale)
    ) {})

    describe("fun toEqual for BigDecimal? and subject is null") {
        it("chooses the correct overload if `null` is passed, does not throw") {
            expect(null as BigDecimal?) toEqual null
        }
    }
}) {

    companion object {
        @Suppress("DEPRECATION")
        fun toEqualBigDecimal(expect: Expect<BigDecimal>, a: BigDecimal): Nothing = expect toEqual a

        @Suppress("DEPRECATION")
        fun toEqualNullable(expect: Expect<BigDecimal?>, a: BigDecimal?): Nothing = expect toEqual a

        fun toEqualNull(expect: Expect<BigDecimal?>, nothing: Nothing?) = expect toEqual nothing

        @Suppress("DEPRECATION")
        fun notToEqual(expect: Expect<BigDecimal>, a: BigDecimal): Nothing = expect notToEqual a

        fun toEqualNumerically(expect: Expect<BigDecimal>, a: BigDecimal) = expect toEqualNumerically a

        fun notToEqualNumerically(expect: Expect<BigDecimal>, a: BigDecimal) = expect notToEqualNumerically a

        fun toEqualIncludingScale(expect: Expect<BigDecimal>, a: BigDecimal) = expect toEqualIncludingScale a

        fun notToEqualIncludingScale(expect: Expect<BigDecimal>, a: BigDecimal) = expect notToEqualIncludingScale a
    }
}
