package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class ListFeatureExtractorSamples {

    @Test
    fun getFeature() {
        val list = listOf(1, 2, 3)

        expect(list) get 0 toBeGreaterThan 0 toBeLessThan 2
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
                it toBeLessThan 0       // still evaluated even though `toBeGreaterThan 2` already fails,
                //                         use `get index` if you want a fail fast behaviour
            }
        }
    }

}
