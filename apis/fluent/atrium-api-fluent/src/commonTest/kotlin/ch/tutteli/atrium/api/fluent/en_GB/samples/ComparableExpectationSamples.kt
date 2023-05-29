package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
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
    fun toBeLessThanOrEqualTo() {
        expect(1).toBeLessThanOrEqualTo(2)
        expect(2).toBeLessThanOrEqualTo(2)

        fails {
            expect(2).toBeLessThanOrEqualTo(1)
        }
    }

    @Test
    fun notToBeGreaterThan() {
        expect(1).notToBeGreaterThan(2)
        expect(2).notToBeGreaterThan(2)

        fails {
            expect(2).notToBeGreaterThan(1)
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

    @Test
    fun toBeGreaterThanOrEqualTo() {
        expect(2).toBeGreaterThanOrEqualTo(1)
        expect(2).toBeGreaterThanOrEqualTo(2)

        fails {
            expect(1).toBeGreaterThanOrEqualTo(2)
        }
    }

    @Test
    fun notToBeLessThan() {
        expect(2).notToBeLessThan(1)
        expect(2).notToBeLessThan(2)

        fails {
            expect(1).notToBeLessThan(2)
        }
    }

    @Test
    fun toBeGreaterThan() {
        expect(2).toBeGreaterThan(1)

        fails {
            expect(1).toBeGreaterThan(2)
        }
    }

}
