package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ComparableExpectationSamples {

    @Test
    fun toBeLessThan() {
        expect(1).toBeLessThan(2)

        fails {
            expect(2).toBeLessThan(1)
        }
    }

    @Test
    fun toBeLessThanOrEqual() {
        expect(1).toBeLessThanOrEqual(2)
        expect(2).toBeLessThanOrEqual(2)

        fails {
            expect(2).toBeLessThanOrEqual(1)
        }
    }

    @Test
    fun toBeGreaterThan() {
        expect(2).toBeGreaterThan(1)

        fails {
            expect(1).toBeGreaterThan(2)
        }
    }

    @Test
    fun toBeGreaterThanOrEqual() {
        expect(2).toBeGreaterThanOrEqual(1)
        expect(2).toBeGreaterThanOrEqual(2)

        fails {
            expect(1).toBeGreaterThanOrEqual(2)
        }
    }

    @Test
    fun toBeEqualComparingTo() {
        expect(2).toBeEqualComparingTo(2)

        fails {
            expect(1).toBeEqualComparingTo(2)
            expect(2).toBeEqualComparingTo(1)
        }
    }
}
