package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.toEqualKeyValue
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapEntryExpectationSamples {

    @Test
    fun toEqualKeyValue() {
        expect(mapOf(1 to "a").entries.first()) toEqualKeyValue (1 to "a")

        fails { // because (1 to "a") is not equal to (1 to "b")
            expect(mapOf(1 to "a").entries.first()) toEqualKeyValue (1 to "b")
        }
    }
}
