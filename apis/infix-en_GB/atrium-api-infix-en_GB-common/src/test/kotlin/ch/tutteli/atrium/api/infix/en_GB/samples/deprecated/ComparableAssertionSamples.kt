//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB.samples.deprecated

import ch.tutteli.atrium.api.infix.en_GB.isEqualComparingTo
import ch.tutteli.atrium.api.infix.en_GB.isGreaterThan
import ch.tutteli.atrium.api.infix.en_GB.isLessThan
import ch.tutteli.atrium.api.infix.en_GB.isLessThanOrEqual
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ComparableAssertionSamples {

    @Test
    fun isLessThan() {
        expect(1) isLessThan 2

        fails {
            expect(2) isLessThan 1
        }
    }

    @Test
    fun isLessThanOrEqual() {
        expect(1) isLessThanOrEqual 2
        expect(2) isLessThanOrEqual 2

        fails {
            expect(2) isLessThanOrEqual 1
        }
    }

    @Test
    fun isGreaterThan() {
        expect(2) isGreaterThan 1

        fails {
            expect(2) isGreaterThan 2
        }
    }

    @Test
    fun isGreaterThanOrEqual() {
        expect(2) isEqualComparingTo 2

        fails {
            expect(1) isEqualComparingTo 2
            expect(2) isEqualComparingTo 1
        }
    }

    @Test
    fun isEqualComparingTo() {
        expect(2) isEqualComparingTo 2

        fails {
            expect(1) isEqualComparingTo 2
            expect(2) isEqualComparingTo 1
        }
    }
}
