package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.toBe
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.test.Test

class FormatFloatingPointNumberTest {

    //TODO move to common-tests formatting floating points should be the same on all platforms
    //TODO formatting should be done via ObjectFormatter where the formatter takes the Locale of the user into account
    @Test
    fun float_notRoundedPrecisionNotAlwaysOk() {
        assert(formatFloatingPointNumber(1.0f - 0.01f)) toBe "0.99"
        assert(formatFloatingPointNumber(0.2f + 0.4f)) toBe "0.6000000000000001"
    }

    @Test
    fun double_notRoundedAndPrecisionOk() {
        assert(formatFloatingPointNumber(1.0 - 0.0000001)) toBe "0.9999999"
    }

    @Test
    fun float_largeNumber_noThousandSignPrecisionNotAlwaysOk() {
        assert(formatFloatingPointNumber(2000_000.0f - 1000_000.0f)) toBe "1000000"
        assert(formatFloatingPointNumber(2000_000.1f - 1000_000.1f)) toBe "1000000.0000000001"
    }

    @Test
    fun double_largeNumber_noThousandSignPrecisionNotAlwaysOk() {
        assert(formatFloatingPointNumber(2000_000_000.0 - 1000_000_000.0)) toBe "1000000000"
        assert(formatFloatingPointNumber(2000_000_000.1 - 1000_000_000.1)) toBe "999999999.9999999"
    }
}
