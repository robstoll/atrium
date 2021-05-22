package ch.tutteli.atrium.api.infix.en_GB.samples.deprecated

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapEntryAssertionSamples {

    @Test
    fun isKeyValue() {
        expect(mapOf(1 to "a").entries.first()) toEqualKeyValue (1 to "a")

        fails {
            expect(mapOf(1 to "a").entries.first()) toEqualKeyValue (1 to "b")
        }
    }

    @Test
    fun keyFeature() {
        expect(mapOf(1 to "a").entries.first()) toEqualKey 1

        fails {
            expect(mapOf(1 to "a").entries.first()) toEqualKey 2
        }
    }

    @Test
    fun key() {
        expect(mapOf(1 to "a").entries.first()) toEqualKey 1

        fails {
            expect(mapOf(1 to "a").entries.first()) key {
                this toEqual 2
            }
        }
    }

    @Test
    fun valueFeature() {
        expect(mapOf(1 to "a").entries.first()) toEqualValue "a"

        fails {
            expect(mapOf(1 to "a").entries.first()) toEqualValue "b"
        }
    }

    @Test
    fun value() {
        expect(mapOf(1 to "a").entries.first()) toEqualValue "a"


        fails {
            expect(mapOf(1 to "a").entries.first()) value {
                this toEqual "b"
            }
        }
    }

}
