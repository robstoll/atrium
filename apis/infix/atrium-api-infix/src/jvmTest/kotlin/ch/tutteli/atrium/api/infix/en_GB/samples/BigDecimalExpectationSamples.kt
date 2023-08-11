package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import java.math.BigDecimal
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
}
