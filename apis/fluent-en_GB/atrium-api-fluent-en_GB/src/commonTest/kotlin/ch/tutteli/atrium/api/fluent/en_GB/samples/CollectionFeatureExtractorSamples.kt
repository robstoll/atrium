package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class CollectionFeatureExtractorSamples {

    @Test
    fun sizeFeature() {
        expect(listOf(1, 2, 3))
            .size               // subject is now of type Int (actually 3)
            .toBeGreaterThan(1) // subject is still of type Int (still 3)
            .toBeLessThan(4)

        fails {
            expect(listOf(1, 2, 3))
                .size
                .toBeLessThan(1)    // fails
                .toBeGreaterThan(4) // not evaluated/reported because `toBeLessThan` already fails
            //                         use `.size { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun size() {
        expect(listOf(1, 2, 3))
            .size { // subject inside this expectation-group is of type Int (actually 3)
                toBeGreaterThan(1)
            } // subject here is back to type List<Int, String>
            .size {
                toBeLessThan(4)
            }

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(listOf(1, 2, 3))
                .size {
                    toBeLessThan(1)    // fails
                    toBeGreaterThan(4) // still evaluated even though `toBeLessThan` already fails,
                    //                    use `.size.` if you want a fail fast behaviour
                }
        }
    }
}
