package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.size
import ch.tutteli.atrium.api.infix.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.infix.en_GB.toBeLessThan
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class CollectionFeatureExtractorSamples {
    @Test
    fun sizeFeature() {
        expect(listOf(1, 2, 3))
            .size toBeGreaterThan 1 toBeLessThan 4
        //       |                 |             | subject is still of type Int (still 3)
        //       |                 | subject is still of type Int (still 3)
        //       | subject is now of type Int (actually 3)

        fails {
            expect(listOf(1, 2, 3))
                .size toBeLessThan 1 toBeGreaterThan 4
            //       |              |         | not reported because `isLessThan 1` already fails
            //       |              | fails
            //       | subject is now of type Int (actually 3)
        }
    }

    @Test
    fun size() {
        expect(listOf(1, 2, 3))
            .size { // subject inside this block is of type Int (actually 3)
                it toBeGreaterThan 1
            } // subject here is back to type List<Int>
            .size { // subject inside this block is of type Int (actually 3)
                it toBeLessThan 4
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(listOf(1, 2, 3)) size { // subject inside this block is of type Int (actually 3)
                it toBeLessThan 1     // fails
                it toBeGreaterThan 4  // isLessThan 1 fails, but isGreaterThan 4 still evaluated
                //                       use `.size.` if you want a fail fast behaviour
            }
        }
    }
}
