package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.toBe
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.test.Test

class FormatNumberTest {

    @Test
    fun float_notRoundedAndPrecisionOk() {
        assert(formatNumber(1.0f - 0.01f)).toBe("0.99")
    }

    @Test
    fun double_notRoundedAndPrecisionOk() {
        assert(formatNumber(1.0 - 0.0000001)).toBe("0.9999999")
    }
}
