package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.toBeInRange
import kotlin.test.Test
import ch.tutteli.atrium.api.verbs.expect

class RangeExpectationSamples {
    @Test
    fun rangeExpectationSampleInt() {
        expect(3) toBeInRange 1..5

        fails {
            expect(0) toBeInRange 1..5
        }
    }

    @Test
    fun rangeExpectationSampleLong() {
        expect(100L) toBeInRange 50L..150L

        fails {
            expect(0L) toBeInRange 50L..150L
        }
    }

    @Test
    fun rangeExpectationSampleChar() {
        expect('c') toBeInRange 'a'..'f'

        fails {
            expect('A') toBeInRange 'a'..'f'
        }
    }
}
