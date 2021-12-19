//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.samples.fails
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
            .isGreaterThan(1) // subject is still of type Int (still 3)
            .isLessThan(4)

        fails {
            expect(listOf(1, 2, 3))
                .size
                .isLessThan(1)    // fails
                .isGreaterThan(4) // not evaluated/reported because `isLessThan(1)` already fails
            // use `size { ... }` if you want that all assertions are evaluated
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
        }
    }
}
