package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class AnyAssertionSamples {

    @Test
    fun toBe() {
        expect(12).toBe(12) // holds

        fails {
            expect(12).toBe(11)
        }

        // holds, toBe is based on equality, use isSameAs for identity
        expect(listOf(1)).toBe(listOf(1))

        fails { // because array has not implemented equals, so is equivalent to isSameAs
            expect(arrayOf(1)).toBe(arrayOf(1))
        }
    }

    @Test
    fun because() {
        expect(12).because("this is true as anything can ever be") {
            toBe(12)
        }

        fails {
            expect(12).because("won't hold, it's not equal") {
                toBe(11)
            }
        }

        expect(listOf(1)).because("toBe is based on equality, use isSameAs for identity") {
            toBe(listOf(1))
        }

        fails {
            expect(arrayOf(1)).because("array has not implemented equals, so is equivalent to isSameAs") {
                toBe(arrayOf(1))
            }
        }
    }
}
