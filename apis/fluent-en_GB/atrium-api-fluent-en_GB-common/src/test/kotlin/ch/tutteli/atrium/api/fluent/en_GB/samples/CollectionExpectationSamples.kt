package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CollectionExpectationSamples {

    @Test
    fun toBeEmpty() {
        expect(listOf<Int>()).toBeEmpty()

        fails {
            expect(listOf(1, 2, 3)).toBeEmpty()
        }
    }

    @Test
    fun notToBeEmpty() {
        expect(listOf(1, 2, 3)).notToBeEmpty()

        fails {
            expect(listOf<Int>()).notToBeEmpty()
        }
    }

    @Test
    fun toHaveSize() {
        expect(listOf(1, 2, 3)).toHaveSize(3)

        fails {
            expect(listOf(1, 2, 3)).toHaveSize(1)
        }
    }
}
