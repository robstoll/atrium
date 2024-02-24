package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class ListFeatureExtractorSamples {

    @Test
    fun getFeature() {
        val list = listOf(1, 2, 3)

        expect(list) get 0 toBeGreaterThan 0 toBeLessThan 2
        //               |                 | subject is still of type Int (still 1)
        //               | subject is now of type Int (actually 1)

        fails {
            expect(list) get 3 toBeLessThan 0
            //             |        | not reported
            //             | fails because index 3 is out of bound
            // use `get index(elementIndex) { ... }` if you want that all expectations are evaluated
        }

        fails {
            expect(list) get 0 toBeGreaterThan 2 toBeLessThan 0
            //               |        |            | not reported because `toBeGreaterThan 2` already fails
            //               |        | fails
            //               | subject is now of type Int (actually 1)
            expect(list) get 0 toBeLessThan 0
            // use `get index(elementIndex) { ... }` if you want that all expectations are evaluated

        }
    }

    @Test
    fun get() {
        val list = listOf(1, 2, 3)

        expect(list) get index(0) {  // subject inside this expectation-group is of type Int (actually 1)
            it toBeLessThan 2
            it toBeGreaterThan 0
        }

        expect(list) get index(1) {  // subject inside this expectation-group is of type Int (actually 2)
            it toBeLessThan 3
            it toBeGreaterThan 1
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(list) get index(0) {
                it toBeGreaterThan 2    // fails
                it toBeLessThan 0       // still evaluated even though `isGreaterThan(2)` already fails,
                //                         use `get index` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun lastFeature() {
        val list = listOf(1, 2, 3)

        expect(list) last o toEqual 3 // subject is 3

        expect(list) last o toBeGreaterThan(2) toBeLessThan(4) // subject is 3 and passes all expectations

        fails {
            expect(list) last o  toBeGreaterThan (3) toBeLessThan (4) // subject is 3, fails on first expectation, second is skipped
        }

        fails {
            expect(emptyList<Int>()) last o toEqual 3 // fails, because list is empty
        }
    }

    @Test
    fun last() {
        val list = listOf(1, 2, 3)

        expect(list) last {
                it toEqual 3 // subject is 3
            } last {
                it toBeGreaterThan(2)
                it toBeLessThan(4) // subject is 3 and passes all expectations
            }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(list).last { // subject within this expectation-group is of type Int (actually 3)
                it toBeGreaterThan (3)  // fails
                it toBeLessThan (4)     // still evaluated, even though  `toBeGreaterThan` already fails,
                //                      use `.last.` if you want a fail fast behaviour
            } // subject here is back type List<Int>
        }

        fails {
            expect(emptyList<Int>()).last {
                it toEqual 3 // fails, because list is empty
            }
        }
    }
}
