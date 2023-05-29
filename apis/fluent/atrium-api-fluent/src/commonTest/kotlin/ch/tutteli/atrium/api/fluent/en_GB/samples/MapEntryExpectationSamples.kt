package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapEntryExpectationSamples {

    @Test
    fun toEqualKeyValue() {
        expect(mapOf(1 to "a").entries.first()).toEqualKeyValue(1, "a")

        fails {
            expect(mapOf(1 to "a").entries.first()).toEqualKeyValue(1, "b")
        }
    }
}
