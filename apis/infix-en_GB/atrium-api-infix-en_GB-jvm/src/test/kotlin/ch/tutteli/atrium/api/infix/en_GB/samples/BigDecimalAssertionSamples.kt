package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.math.BigDecimal
import kotlin.test.Test

class BigDecimalAssertionSamples {

    @Test
    fun toBe() {
        // only use toBe to check against null, otherwise use isNumericallyEqualTo or isEqualIncludingScale
        expect(null as BigDecimal?).toBe(null)
    }
}
