package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.math.BigDecimal
import kotlin.test.Test

class BigDecimalExpectationSamples {

    @Test
    fun toEqual() {
        // only use toEqual to check against null, otherwise use toEqualNumerically or toEqualIncludingScale
        expect(null as BigDecimal?).toEqual(null)
    }
}
