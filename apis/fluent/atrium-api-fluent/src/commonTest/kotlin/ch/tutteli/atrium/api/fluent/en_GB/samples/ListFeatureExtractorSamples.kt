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
    fun lastFeature() {
        val list = listOf(1, 2, 3)

        expect(list).last.toEqual(3) // subject is 3

        expect(list).last // Subject is 3
            .toBeGreaterThan(2) // subject is still 3
            .toBeLessThan(4) // subject is still 3

        fails {
            expect(list).last.toBeGreaterThan(3).toBeLessThan (4) // subject is 3, fails on first expectation, second is skipped
        }

        fails {
            expect(emptyList<Int>()).last.toEqual(3) // fails, because list is empty
        }
    }

    @Test
    fun last() {
        val list = listOf(1, 2, 3)

        expect(list)
            .last {
                toEqual(3) // subject is 3
            }
            .last { // subject is 3
                toBeGreaterThan(2) // subject is still 3
                toBeLessThan(4) // subject is still 3
            }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(list).last { // subject within this expectation-group is of type Int (actually 3)
                toBeGreaterThan(3)   // fails
                toBeLessThan (4)     // still evaluated, even though  `toBeGreaterThan` already fails,
                //                      use `.last.` if you want a fail fast behaviour
            } // subject here is back type List<Int>
        }

        fails {
            expect(emptyList<Int>()).last {
                toEqual(3) // fails, because list is empty
            }
        }
    }
}
