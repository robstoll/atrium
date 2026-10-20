package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.toBeIn
import kotlin.test.Test
import ch.tutteli.atrium.api.verbs.expect

class RangeExpectationSamples {

    @Test
    fun toBeInCharRange() {
        expect('c') toBeIn 'a'..'f'

        fails {
            expect('A') toBeIn 'a'..'f'
        }
    }

    @Test
    fun toBeInIntRange() {
        expect(3) toBeIn 1..5

        fails {
            expect(0) toBeIn 1..5
        }
    }

    @Test
    fun toBeInLongRange() {
        expect(100L) toBeIn 50L..150L

        fails {
            expect(0L) toBeIn 50L..150L
        }
    }

}
