package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.contains
import ch.tutteli.atrium.api.fluent.en_GB.containsNot
import ch.tutteli.atrium.api.fluent.en_GB.hasSize
import ch.tutteli.atrium.api.fluent.en_GB.isEmpty
import ch.tutteli.atrium.api.fluent.en_GB.isGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.isLessThan
import ch.tutteli.atrium.api.fluent.en_GB.isNotEmpty
import ch.tutteli.atrium.api.fluent.en_GB.message
import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.size
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CollectionAssertionSamples {

    @Test
    fun isEmpty() {
        expect(listOf<Int>()).isEmpty()

        fails {
            expect(listOf(1, 2, 3)).isEmpty()
        }
    }

    @Test
    fun isNotEmpty() {
        expect(listOf(1, 2, 3)).isNotEmpty()

        fails {
            expect(listOf<Int>()).isNotEmpty()
        }
    }

    @Test
    fun hasSize() {
        expect(listOf(1, 2, 3)).hasSize(3)

        fails {
            expect(listOf(1, 2, 3)).hasSize(1)
        }
    }

    @Test
    fun sizeFeature() {
        expect(listOf(1, 2, 3)).size // subject of the assertion is now the size of the list, 3 here
            .isGreaterThan(1) // subject of the assertion is still 3
            .isLessThan(4)

        fails {
            expect(listOf(1, 2, 3)).size
                .isLessThan(1)
                .isGreaterThan(4) // not reported, use `size { ... }` if you want that all assertions are evaluated
        }.message {
            contains("is less than: 1")
            containsNot("is greater than: 4")
        }
    }

    @Test
    fun size() {
        expect(listOf(1, 2, 3))
            .size { // subject inside this block is now 3
                isGreaterThan(1)
            } // subject here is still list
            .size { // subject inside this block is 3
                isLessThan(4)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(listOf(1, 2, 3))
                .size {
                    isLessThan(1)
                    isGreaterThan(4) // still evaluated even though isLessThan(1) already fails,
                    // use the `.size.` if you want a fail fast behaviour
                }
        }.messageContains("is less than: 1", "is greater than: 4")
    }
}
