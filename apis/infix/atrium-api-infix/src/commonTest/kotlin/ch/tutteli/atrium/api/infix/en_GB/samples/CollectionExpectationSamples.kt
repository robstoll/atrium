package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.empty
import ch.tutteli.atrium.api.infix.en_GB.notToBe
import ch.tutteli.atrium.api.infix.en_GB.toBe
import ch.tutteli.atrium.api.infix.en_GB.toHaveSize
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CollectionExpectationSamples {
    @Test
    fun toBeEmpty() {
        expect(emptyList<Int>()) toBe empty

        fails {
            expect(listOf(1, 2, 3)) toBe empty
        }
    }

    @Test
    fun notToBeEmpty() {
        expect(listOf(1, 2, 3)) notToBe empty

        fails {
            expect(emptyList<Int>()) notToBe empty
        }
    }

    @Test
    fun toHaveSize() {
        expect(listOf(1, 2, 3)) toHaveSize 3

        fails {
            expect(listOf(1, 2, 3)) toHaveSize 1
        }
    }
}
