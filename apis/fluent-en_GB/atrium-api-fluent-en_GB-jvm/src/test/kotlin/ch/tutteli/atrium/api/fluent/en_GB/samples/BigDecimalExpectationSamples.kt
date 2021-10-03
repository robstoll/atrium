package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.math.BigDecimal
import kotlin.test.Test

class BigDecimalExpectationSamples {

    @Test
    fun toEqual() {
        // only use toEqual to check against null, otherwise use toEqualNumerically or toEqualIncludingScale
        expect(null as BigDecimal?).toEqual(null)
    }


    @Test
    fun notToEqual() {
        // only use notToEqual to check against null, otherwise use notToEqualNumerically or notToEqualIncludingScale
        expect(null as BigDecimal?).notToEqual(BigDecimal("-12345.6789"))
        expect(null as BigDecimal?).notToEqual(BigDecimal("1.0"))
        expect(null as BigDecimal?).notToEqual(BigDecimal("25.0"))


        expect(null as BigDecimal?).notToEqual(BigDecimal(-12345.6789))
        expect(null as BigDecimal?).notToEqual(BigDecimal(1.0))
        expect(null as BigDecimal?).notToEqual(BigDecimal(25.0))
    }


    @Test
    fun toEqualNumerically() {
        expect(BigDecimal("10")).toEqualNumerically(BigDecimal("10.0"))
        expect(BigDecimal("1213669989183")).toEqualNumerically(BigDecimal("1213669989183"))
        expect(BigDecimal("1238126387123")).toEqualNumerically(BigDecimal("1238126387123"))


        expect(BigDecimal(10)).toEqualNumerically(BigDecimal(10.0))
        expect(BigDecimal(1213669989183)).toEqualNumerically(BigDecimal(1213669989183))
        expect(BigDecimal(1238126387123)).toEqualNumerically(BigDecimal(1238126387123))
    }


    @Test
    fun notToEqualNumerically() {
        expect(BigDecimal("10")).notToEqualNumerically(BigDecimal("1213669989183"))
        expect(BigDecimal("1213669989183")).notToEqualNumerically(BigDecimal("10"))
        expect(BigDecimal("1238126387123")).notToEqualNumerically(BigDecimal("-1.0"))

        expect(BigDecimal(10)).notToEqualNumerically(BigDecimal(1213669989183))
        expect(BigDecimal(1213669989183)).notToEqualNumerically(BigDecimal(10))
        expect(BigDecimal(1238126387123)).notToEqualNumerically(BigDecimal(-1.0))
    }


    @Test
    fun toEqualIncludingScale() {
        expect(BigDecimal("10.0")).toEqualIncludingScale(BigDecimal("10.0"))
        expect(BigDecimal("456.0")).toEqualIncludingScale(BigDecimal("456.0"))
        expect(BigDecimal("-1.456")).toEqualIncludingScale(BigDecimal("-1.456"))

        expect(BigDecimal(10.0)).toEqualIncludingScale(BigDecimal(10.0))
        expect(BigDecimal(456.0)).toEqualIncludingScale(BigDecimal(456.0))
    }


    @Test
    fun notToEqualIncludingScale() {
        expect(BigDecimal("10")).notToEqualIncludingScale(BigDecimal("10.0"))
        expect(BigDecimal("456.0")).notToEqualIncludingScale(BigDecimal("456"))
        expect(BigDecimal("-1.456")).notToEqualIncludingScale(BigDecimal("-1.45"))

        expect(BigDecimal(10)).notToEqualIncludingScale(BigDecimal(10.01))
        expect(BigDecimal(456.01)).notToEqualIncludingScale(BigDecimal(456))
        expect(BigDecimal(-1.456)).notToEqualIncludingScale(BigDecimal(-1.45))
    }


}
