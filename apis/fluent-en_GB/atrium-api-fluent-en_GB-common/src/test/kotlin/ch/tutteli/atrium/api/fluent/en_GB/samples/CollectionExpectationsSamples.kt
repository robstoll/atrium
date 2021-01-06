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
        expect(listOf(1, 2, 3))
            .size             // subject is now of type Int (actually 3)
            .isGreaterThan(1) // subject is still of type Int (still 1)
            .isLessThan(4)

        fails {
            expect(listOf(1, 2, 3))
                .size
                .isLessThan(1)    // fails
                .isGreaterThan(4) // not reported because `isLessThan(1)` already fails
                                  // use `size { ... }` if you want that all assertions are evaluated
        }.message {
            contains("is less than: 1")
            containsNot("is greater than: 4")
        }
    }

    @Test
    fun size() {
        expect(listOf(1, 2, 3))
            .size { // subject inside this block is of type Int (actually 3)
                isGreaterThan(1)
            } // subject here is back to type List<Int, String>
            .size {
                isLessThan(4)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(listOf(1, 2, 3))
                .size {
                    isLessThan(1)    // fails
                    isGreaterThan(4) // still evaluated even though `isLessThan(1)` already fails,
                                     // use `.size.` if you want a fail fast behaviour
                }
        }.messageContains(
            "is less than: 1",
            "is greater than: 4"
        )
    }
}
