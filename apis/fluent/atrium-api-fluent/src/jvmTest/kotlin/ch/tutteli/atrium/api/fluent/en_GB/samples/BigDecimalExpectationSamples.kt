package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import java.math.BigDecimal
import kotlin.test.Test

class BigDecimalExpectationSamples {

    @Test
    fun toEqualNull() {
        // Use toEqual only to check against null if your subject is a BigDecimal
        // Use toEqualNumerically or toEqualIncludingScale if you want to compare it against another BigDecimal
        expect(null as BigDecimal?).toEqual(null)

        fails {
            expect(BigDecimal("-12345.6789") as BigDecimal?).toEqual(null)
        }
    }

    @Test
    fun notToEqualNull() {
        // Use notToEqual only to check against null if your subject is a BigDecimal
        // Use notToEqualNumerically or notToEqualIncludingScale if you want to compare it against another BigDecimal
        expect(BigDecimal("-12345.6789") as BigDecimal?).notToEqual(null)

        fails {
            expect(null as BigDecimal?).notToEqual(null)
        }
    }

    @Test
    fun toEqualNumerically() {
        expect(BigDecimal("10")).toEqualNumerically(BigDecimal("10.0"))
        expect(BigDecimal("1213669989183")).toEqualNumerically(BigDecimal("1213669989183"))
        expect(BigDecimal("1238126387123")).toEqualNumerically(BigDecimal("1238126387123"))


        expect(BigDecimal(10)).toEqualNumerically(BigDecimal(10.0))
        expect(BigDecimal(1213669989183)).toEqualNumerically(BigDecimal(1213669989183))
        expect(BigDecimal(1238126387123)).toEqualNumerically(BigDecimal(1238126387123))

        fails {
            expect(BigDecimal(10)).toEqualNumerically(BigDecimal(10.1))
        }
    }


    @Test
    fun notToEqualNumerically() {
        expect(BigDecimal("10")).notToEqualNumerically(BigDecimal("1213669989183"))
        expect(BigDecimal("1213669989183")).notToEqualNumerically(BigDecimal("10"))
        expect(BigDecimal("1238126387123")).notToEqualNumerically(BigDecimal("-1.0"))

        expect(BigDecimal(10)).notToEqualNumerically(BigDecimal(1213669989183))
        expect(BigDecimal(1213669989183)).notToEqualNumerically(BigDecimal(10))
        expect(BigDecimal(1238126387123)).notToEqualNumerically(BigDecimal(-1.0))

        fails {
            // because is numerically the same
            // use notToEqualIncludingScale if you want to compare the scale as well
            expect(BigDecimal("10")).notToEqualNumerically(BigDecimal("10.0"))
        }
    }


    @Test
    fun toEqualIncludingScale() {
        expect(BigDecimal("10.0")).toEqualIncludingScale(BigDecimal("10.0"))
        expect(BigDecimal("456.0")).toEqualIncludingScale(BigDecimal("456.0"))
        expect(BigDecimal("-1.456")).toEqualIncludingScale(BigDecimal("-1.456"))

        expect(BigDecimal(10.0)).toEqualIncludingScale(BigDecimal(10.0))
        expect(BigDecimal(456.0)).toEqualIncludingScale(BigDecimal(456.0))

        fails {
            // because scale is different, use toEqualNumerically if you want to ignore the scale
            expect(BigDecimal("10")).toEqualIncludingScale(BigDecimal("10.0"))
        }
    }


    @Test
    fun notToEqualIncludingScale() {
        expect(BigDecimal("10")).notToEqualIncludingScale(BigDecimal("10.0"))
        expect(BigDecimal("456.0")).notToEqualIncludingScale(BigDecimal("456"))
        expect(BigDecimal("-1.456")).notToEqualIncludingScale(BigDecimal("-1.45"))

        expect(BigDecimal(10)).notToEqualIncludingScale(BigDecimal(10.01))
        expect(BigDecimal(456.01)).notToEqualIncludingScale(BigDecimal(456))
        expect(BigDecimal(-1.456)).notToEqualIncludingScale(BigDecimal(-1.45))

        fails {
            expect(BigDecimal("10.0")).notToEqualIncludingScale(BigDecimal("10.0"))
        }
    }
}
