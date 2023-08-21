package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import java.math.BigDecimal
import java.math.MathContext
import kotlin.test.Test

class BigDecimalExpectationSamples {

    @Test
    fun toEqualNull() {
        // Use toEqual only to check against null if your subject is a BigDecimal
        // Use toEqualNumerically or toEqualIncludingScale if you want to compare it against another BigDecimal
        expect(null as BigDecimal?) toEqual null

        fails {
            expect(BigDecimal("-12345.6789") as BigDecimal?) toEqual null
        }
    }

    @Test
    fun notToEqualNull() {
        // Use notToEqual only to check against null if your subject is a BigDecimal
        // Use notToEqualNumerically or notToEqualIncludingScale if you want to compare it against another BigDecimal
        expect(BigDecimal("-12345.6789") as BigDecimal?) notToEqual null

        fails {
            expect(null as BigDecimal?) notToEqual null
        }
    }

    @Test
    fun toEqualNumerically(){
        // Use toEqualNumerically to compare subject and target are numerically equal
        expect(BigDecimal("-12345.67890")) toEqualNumerically BigDecimal("-12345.6789")

        fails{
            expect(BigDecimal("-12345.678")) toEqualNumerically BigDecimal("-12345.6789")
        }
    }

    @Test
    fun notToEqualNumerically(){
        // Use notToEqualNumerically to compare subject and target are not numerically equal
        expect(BigDecimal("-12345.678")) notToEqualNumerically BigDecimal("-12345.6789")

        fails{
            expect(BigDecimal("-12345.67890")) notToEqualNumerically BigDecimal("-12345.6789")
        }
    }

    @Test
    fun toEqualIncludingScale(){
        // Use toEqualIncludingScale to compare subject and target are numerically equal including scale
        expect(BigDecimal("-12345.678912", MathContext(9))) toEqualIncludingScale BigDecimal("-12345.6789")

        fails{
            expect(BigDecimal("-12345.67890")) toEqualIncludingScale BigDecimal("-12345.6789")
        }
    }

    @Test
    fun notToEqualIncludingScale(){
        // Use notToEqualIncludingScale to compare subject and target are not numerically and scaling equal
        expect(BigDecimal("-12345.67890")) notToEqualIncludingScale BigDecimal("-12345.6789")

        fails{
            expect(BigDecimal("-12345.678912", MathContext(9))) notToEqualIncludingScale BigDecimal("-12345.6789")
        }
    }
}
