//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB.samples.deprecated

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapEntryAssertionSamples {

    @Test
    fun isKeyValue() {
        expect(mapOf(1 to "a").entries.first()) isKeyValue  (1 to "a")

        fails {
            expect(mapOf(1 to "a").entries.first()) isKeyValue (1 to "b")
        }
    }
}
