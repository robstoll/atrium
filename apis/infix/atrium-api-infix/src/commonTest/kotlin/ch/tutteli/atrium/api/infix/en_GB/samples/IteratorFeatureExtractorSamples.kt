package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IteratorFeatureExtractorSamples {

    @Test
    fun nextFeature() {
        val iterator = listOf(1, 2, 3).iterator()

        expect(iterator) next o toBeGreaterThan 0 toBeLessThan 2
        //                    | subject is now of type Int (actually 1)

        fails {
            expect(iterator) next o toBeGreaterThan 1 toBeLessThan 0
            //                 |                         | fails because subject is 2
            //                 | subject is now of type Int (actually 2)
        }

        fails {
            expect(iterator) next o toBeGreaterThan 4 toBeLessThan 0
            //                 |           |               | not reported because `toBeGreaterThan 4` already failed
            //                 |           | fails because subject is 3
            //                 | subject is now of type Int (actually 3)
        }

        fails {
            expect(iterator) next o toEqual 4
            //                 |       | not reported because `next` already
            //                 | fails as the list has only 3 elements
            expect(iterator) next o toEqual 5
            // use `.next { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun next() {
        val iterator = listOf(1, 2, 3).iterator()

        expect(iterator) next {      // subject inside this expectation-group is of type Int (actually 1)
            it toBeLessThan 2
            it toBeGreaterThan 0
        }

        expect(iterator) next {      // subject inside this expectation-group is of type Int (actually 2)
            it toBeLessThan 3
            it toBeGreaterThan 1
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(iterator) next {  // subject inside this expectation-group is of type Int (actually 3)
                it toBeGreaterThan 4 // fails because subject is 3
                it toBeLessThan 2    // still evaluated even though `toBeGreaterThan 4` already fails,
                //                      use `next o` if you want a fail fast behaviour
            }

        }
    }
}
