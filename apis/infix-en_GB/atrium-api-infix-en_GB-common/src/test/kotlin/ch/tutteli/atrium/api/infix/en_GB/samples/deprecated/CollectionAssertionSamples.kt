//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB.samples.deprecated

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CollectionAssertionSamples {
    @Test
    fun isEmpty() {
        expect(listOf<Int>()) toBe empty

        fails {
            expect(listOf(1, 2, 3)) toBe empty
        }
    }

    @Test
    fun isNotEmpty() {
        expect(listOf(1, 2, 3)) notToBe empty

        fails {
            expect(listOf<Int>()) notToBe empty
        }
    }

    @Test
    fun hasSize() {
        expect(listOf(1, 2, 3)) hasSize 3

        fails {
            expect(listOf(1, 2, 3)) hasSize 1
        }
    }

    @Test
    fun sizeFeature() {
        expect(listOf(1, 2, 3))
            .size isGreaterThan 1 isLessThan 4
            //|         |             | subject is still of type Int (still 3)
            //|         | subject is still of type Int (still 3)
            //| subject is now of type Int (actually 3)

        fails {
            expect(listOf(1, 2, 3))
                .size isLessThan 1 isGreaterThan 4
                //|       |              | not reported because `isLessThan 1` already fails
                //|       | fails
                //| subject is now of type Int (actually 3)
        }
    }

    @Test
    fun size() {
        expect(listOf(1, 2, 3))
            .size { // subject inside this block is of type Int (actually 3)
                it isGreaterThan 1
            } // subject here is back to type List<Int>
            .size { // subject inside this block is of type Int (actually 3)
                it isLessThan 4
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(listOf(1, 2, 3)) size { // subject inside this block is of type Int (actually 3)
                it isLessThan 1     // fails
                it isGreaterThan 4  // isLessThan 1 fails, but isGreaterThan 4 still evaluated
                                    // use `.size.` if you want a fail fast behaviour
            }
        }
    }
}
