package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class FormatFloatingPointNumberTest {

    //TODO 1.3.0 move to common-tests formatting floating points should be the same on all platforms
    //TODO 1.3.0 formatting should be done via ObjectFormatter where the formatter takes the Locale of the user into account
    @Test
    fun float_notRoundedPrecisionNotAlwaysOk() {
        expect(formatFloatingPointNumber(1.0f - 0.01f)) toEqual "0.99"
        expect(formatFloatingPointNumber(0.2f + 0.4f)) toEqual "0.6000000000000001"
    }

    @Test
    fun double_notRoundedAndPrecisionOk() {
        expect(formatFloatingPointNumber(1.0 - 0.0000001)) toEqual "0.9999999"
    }

    @Test
    fun float_largeNumber_noThousandSignPrecisionNotAlwaysOk() {
        expect(formatFloatingPointNumber(2000_000.0f - 1000_000.0f)) toEqual "1000000"
        expect(formatFloatingPointNumber(2000_000.1f - 1000_000.1f)) toEqual "1000000.0000000001"
    }

    @Test
    fun double_largeNumber_noThousandSignPrecisionNotAlwaysOk() {
        expect(formatFloatingPointNumber(2000_000_000.0 - 1000_000_000.0)) toEqual "1000000000"
        expect(formatFloatingPointNumber(2000_000_000.1 - 1000_000_000.1)) toEqual "999999999.9999999"
    }
}
