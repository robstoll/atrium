package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CollectionExpectationSamples {

    @Test
    fun toBeEmpty() {
        expect(emptyList<Int>()).toBeEmpty()

        fails {
            expect(listOf(1, 2, 3)).toBeEmpty()
        }
    }

    @Test
    fun notToBeEmpty() {
        expect(listOf(1, 2, 3)).notToBeEmpty()

        fails {
            expect(emptyList<Int>()).notToBeEmpty()
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
