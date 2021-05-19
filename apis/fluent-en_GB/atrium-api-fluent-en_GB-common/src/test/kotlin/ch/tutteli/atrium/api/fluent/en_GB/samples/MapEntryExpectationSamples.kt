package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapEntryExpectationSamples {

    @Test
    fun toEqualKeyValue() {
        expect(mapOf(1 to "a").entries.first()).toEqualKeyValue(1, "a")

        fails {
            expect(mapOf(1 to "a").entries.first()).toEqualKeyValue(1, "b")
        }
    }

    @Test
    fun keyFeature() {
        expect(mapOf(1 to "a").entries.first())
            .key    // subject here is of type Int (actually 1)
            .toEqual(1)

        fails {
            expect(mapOf(1 to "a").entries.first())
                .key    // subject here is of type Int (actually 1)
                .toEqual(2)    // fails because 1 is not equal to 2
        }
    }

    @Test
    fun key() {
        expect(mapOf(1 to "a").entries.first())
            .key {  // subject inside this block is of type Int (actually 1)
                toEqual(1)
            }

        fails {
            expect(mapOf(1 to "a").entries.first())
                .key {  // subject inside this block is of type Int (actually 1)
                    toEqual(2) // fails because 1 is not equal to 2
                }
        }
    }

    @Test
    fun valueFeature() {
        expect(mapOf(1 to "a").entries.first())
            .value  // subject here is of type String (actually "a")
            .toEqual("a")

        fails {
            expect(mapOf(1 to "a").entries.first())
                .value  // subject here is of type String (actually "a")
                .toEqual("b")  // fails because "a" is not equal to "b"
        }
    }

    @Test
    fun value() {
        expect(mapOf(1 to "a").entries.first())
            .value {    // subject inside this block is of type String (actually "a")
                toEqual("a")
            }

        fails {
            expect(mapOf(1 to "a").entries.first())
                .value {    // subject inside this block is of type String (actually "a")
                    toEqual("b")   // fails because "a" is not equal to "b"
                }
        }
    }
}
