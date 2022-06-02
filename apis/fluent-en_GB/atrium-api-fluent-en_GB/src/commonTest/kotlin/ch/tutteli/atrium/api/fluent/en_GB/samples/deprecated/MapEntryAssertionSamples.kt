//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapEntryAssertionSamples {

    @Test
    fun isKeyValue() {
        expect(mapOf(1 to "a").entries.first()).toEqualKeyValue(1, "a")

        fails {
            expect(mapOf(1 to "a").entries.first()).toEqualKeyValue(1, "b")
        }
    }

    @Test
    fun keyFeature() {
        expect(mapOf(1 to "a").entries.first())
            .key    // subject here is of type Int (actually 1)
            .toBe(1)

        fails {
            expect(mapOf(1 to "a").entries.first())
                .key    // subject here is of type Int (actually 1)
                .toBe(2)    // fails because 1 is not equal to 2
        }
    }

    @Test
    fun key() {
        expect(mapOf(1 to "a").entries.first())
            .key {  // subject inside this expectation-group is of type Int (actually 1)
                toBe(1)
            }

        fails {
            expect(mapOf(1 to "a").entries.first())
                .key {  // subject inside this expectation-group is of type Int (actually 1)
                    toBe(2) // fails because 1 is not equal to 2
                }
        }
    }

    @Test
    fun valueFeature() {
        expect(mapOf(1 to "a").entries.first())
            .value  // subject here is of type String (actually "a")
            .toBe("a")

        fails {
            expect(mapOf(1 to "a").entries.first())
                .value  // subject here is of type String (actually "a")
                .toBe("b")  // fails because "a" is not equal to "b"
        }
    }

    @Test
    fun value() {
        expect(mapOf(1 to "a").entries.first())
            .value {    // subject inside this expectation-group is of type String (actually "a")
                toBe("a")
            }

        fails {
            expect(mapOf(1 to "a").entries.first())
                .value {    // subject inside this expectation-group is of type String (actually "a")
                    toBe("b")   // fails because "a" is not equal to "b"
                }
        }
    }
}
