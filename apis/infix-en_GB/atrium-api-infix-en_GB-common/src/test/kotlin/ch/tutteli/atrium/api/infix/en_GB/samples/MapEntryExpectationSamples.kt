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

        fails { // because (1 to "a") is not equal to (1 to "b")
            expect(mapOf(1 to "a").entries.first()) toEqualKeyValue (1 to "b")
        }
    }

    @Test
    fun keyFeature() {
        expect(mapOf(1 to "a").entries.first()).key toEqual 1
        //      |                                | subject here is type of Int (actually 1)
        //      | subject here is of type Map.Entry<Int, String>

        fails { // because 1 is not equal to 2
            expect(mapOf(1 to "a").entries.first()).key toEqual 2
            //      |                                | subject here is type of Int (actually 1)
            //      | subject here is of type Map.Entry<Int, String>
        }
    }

    @Test
    fun key() {
        expect(mapOf(1 to "a").entries.first()).key { // subject inside this block is of type Int (actually 1)
            this toEqual 1
        }

        fails { // because 1 is not equal to 2
            expect(mapOf(1 to "a").entries.first()).key { // subject inside this block is of type Int (actually 1)
                this toEqual 2
            }
        }
    }

    @Test
    fun valueFeature() {
        expect(mapOf(1 to "a").entries.first()).value toEqual "a"
        //     |                                 | subject here is of type String (actually "a")
        //     | subject here is of type Map.Entry<Int, String>

        fails { // because "a" is not equal to "b"
            expect(mapOf(1 to "a").entries.first()).value toEqual "b"
            //     |                                 | subject here is of type String (actually "a")
            //     | subject here is of type Map.Entry<Int, String>
        }
    }

    @Test
    fun value() {
        expect(mapOf(1 to "a").entries.first()) value { // subject inside this block is of type String (actually "a")
            this toEqual "a"
        }

        fails { // because "a" is not equal to "b"
            expect(mapOf(1 to "a").entries.first()) value { // subject inside this block is of type String (actually "a")
                this toEqual "b"
            }
        }
    }
}
