package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.key
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.infix.en_GB.toEqualKeyValue
import ch.tutteli.atrium.api.infix.en_GB.value
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapEntryExpectationSamples {

    @Test
    fun toEqualKeyValue() {
        expect(mapOf(1 to "a").entries.first()) toEqualKeyValue (1 to "a")

        fails {
            expect(mapOf(1 to "a").entries.first()) toEqualKeyValue (1 to "b")
        }
    }

    @Test
    fun keyFeature() {
        // subject here is of type Int (actually 1)
        expect(mapOf(1 to "a").entries.first()).key toEqual 1

        fails {
            // subject here is of type Int (actually 1)
            // fails because 1 is not equal to 2
            expect(mapOf(1 to "a").entries.first()).key toEqual 2
        }
    }

    @Test
    fun key() {
        // subject inside this block is of type Int (actually 1)
        expect(mapOf(1 to "a").entries.first()).key {
            this toEqual 1
        }

        fails {
            // subject inside this block is of type Int (actually 1)
            // fails because 1 is not equal to 2
            expect(mapOf(1 to "a").entries.first()).key {
                this toEqual 2
            }
        }
    }

    @Test
    fun valueFeature() {
        // subject here is of type String (actually "a")
        expect(mapOf(1 to "a").entries.first()).value toEqual ("a")

        fails {
            // subject here is of type String (actually "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a").entries.first()).value toEqual ("b")
        }
    }

    @Test
    fun value() {
        // subject inside this block is of type String (actually "a")
        expect(mapOf(1 to "a").entries.first()) value {
            this toEqual "a"
        }

        fails {
            // subject inside this block is of type String (actually "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a").entries.first()) value {
                this toEqual "b"
            }
        }
    }
}
