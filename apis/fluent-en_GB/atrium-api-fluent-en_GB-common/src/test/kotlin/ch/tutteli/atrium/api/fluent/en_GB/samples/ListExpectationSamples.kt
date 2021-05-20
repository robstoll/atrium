package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ListExpectationSamples {

    @Test
    fun getFeature() {
        val list = listOf(1, 2, 3)

        expect(list)
            .get(0)             // subject is now of type Int (actually 1)
            .toBeGreaterThan(0) // subject is still of type Int (still 1)
            .toBeLessThan(2)

        fails {
            expect(list)
                .get(3)          // fails because index 3 is out of bound
                .toBeLessThan(0) // not reported
            //                      use `get(index) { ... }` if you want that all expectations are evaluated
        }.message {
            toContain("index out of bounds")
            notToContain("is less than: 2")
        }

        fails {
            expect(list)
                .get(0)
                .toBeGreaterThan(2)  // fails
                .toBeLessThan(0)     // not reported because `isGreaterThan(2)` already fails
            //                          use `get(index) { ... }` if you want that all expectations are evaluated
        }.message {
            toContain("is greater than: 2")
            notToContain("is less than: 0")
        }
    }

    @Test
    fun get() {
        val list = listOf(1, 2, 3)

        expect(list)
            .get(0) { // subject inside this block is of type Int (actually 1)
                toBeGreaterThan(0)
                toBeLessThan(2)
            } // subject here is back to type List<Int>
            .get(1) { // subject inside this block is of type Int (actually 2)
                toBeGreaterThan(1)
                toBeLessThan(3)
            }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(list)
                .get(0) {
                    toBeGreaterThan(2) // fails
                    toBeLessThan(0)    // still evaluated even though `isGreaterThan(2)` already fails,
                    //                    use `.get(index).` if you want a fail fast behaviour
                }
        }.messageToContain(
            "is greater than: 2",
            "is less than: 0"
        )
    }
}
