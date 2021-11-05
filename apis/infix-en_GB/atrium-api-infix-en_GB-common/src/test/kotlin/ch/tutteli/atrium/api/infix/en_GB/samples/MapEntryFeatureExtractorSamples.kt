package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.key
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.infix.en_GB.toEqualKeyValue
import ch.tutteli.atrium.api.infix.en_GB.value
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapEntryFeatureExtractorSamples {

    @Test
    fun keyFeature() {
        val entry = mapOf(1 to "a").entries.first()

        expect(entry).key toEqual 1
        //      |      | subject here is type of Int (actually 1)
        //      | subject here is of type Map.Entry<Int, String>

        fails { // because 1 is not equal to 2
            expect(entry).key toEqual 2
            //      |      | subject here is type of Int (actually 1)
            //      | subject here is of type Map.Entry<Int, String>
        }
    }

    @Test
    fun key() {
        val entry = mapOf(1 to "a").entries.first()

        expect(entry).key { // subject inside this block is of type Int (actually 1)
            this toEqual 1
        } // subject here is back to type Map.Entry<Int, String>

        fails { // because 1 is not equal to 2
            expect(entry).key { // subject inside this block is of type Int (actually 1)
                this toEqual 2
            }
        }
    }

    @Test
    fun valueFeature() {
        val entry = mapOf(1 to "a").entries.first()

        expect(entry).value toEqual "a"
        //     |        | subject here is of type String (actually "a")
        //     | subject here is of type Map.Entry<Int, String>

        fails { // because "a" is not equal to "b"
            expect(entry).value toEqual "b"
            //     |        | subject here is of type String (actually "a")
            //     | subject here is of type Map.Entry<Int, String>
        }
    }

    @Test
    fun value() {
        val entry = mapOf(1 to "a").entries.first()

        expect(entry) value { // subject inside this block is of type String (actually "a")
            this toEqual "a"
        } // subject here is back to type Map.Entry<Int, String>

        fails { // because "a" is not equal to "b"
            expect(entry) value { // subject inside this block is of type String (actually "a")
                this toEqual "b"
            }
        }
    }
}
