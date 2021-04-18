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
                .isGreaterThan(4) // not reported because `isLessThan(1)` already fails
                                  // use `size { ... }` if you want that all assertions are evaluated
        }.message {
            toContain("is less than: 1" )
            notToContain("is greater than: 4" )
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
