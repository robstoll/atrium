package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class ListFeatureExtractorSamples {

    @Test
    fun getFeature() {
        val list = listOf(1, 2, 3)

        expect(list)
            .get(0)             // subject is now of type Int (actually 1)
            .toBeGreaterThan(0) // subject is still of type Int (still 1)
            .toBeLessThan(2)

        fails {
            expect(list)
                .get(0)              // subject is now of type Int (actually 1)
                .toBeGreaterThan(2)  // fails
                .toBeLessThan(0)     // not evaluated/reported because `toBeGreaterThan` already fails
            //                          use `.get(index) { ... }` if you want that all expectations are evaluated
        }

        fails {
            expect(list)
                .get(3)          // fails because index 3 is out of bound
                .toBeLessThan(0) // not evaluated/reported because `get` already fails
            //                      use `.get(index) { ... }` if you want that all expectations are evaluated
        }

    }

    @Test
    fun get() {
        val list = listOf(1, 2, 3)

        expect(list)
            .get(0) { // subject inside this expectation-group is of type Int (actually 1)
                toBeGreaterThan(0)
                toBeLessThan(2)
            } // subject here is back to type List<Int>
            .get(1) { // subject inside this expectation-group is of type Int (actually 2)
                toBeGreaterThan(1)
                toBeLessThan(3)
            }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(list).get(0) {
                toBeGreaterThan(2) // fails
                toBeLessThan(0)    // still evaluated even though `toBeGreaterThan` already fails,
                //                    use `.get(index).` if you want a fail fast behaviour
            }
        }


        fails { // because index 3 is out of bound, but since we use an expectation-group...
            expect(list).get(3) {
                toBeLessThan(0) // ...reporting mentions that the element at index 3 was expected `to be less than: 0`
            }
        }
    }

    @Test
    fun last() {
        val list = listOf(1, 2, 3)

        expect(list) { // TODO: comments, fail tests
            last.toEqual(3)
            last {
                toBeGreaterThan(2)
                toBeLessThan(4)
            }
        }
    }
}
