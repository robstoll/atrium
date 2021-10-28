package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class PairFeatureExtractorSamples {

    @Test
    fun firstFeature() {
        val pair = 1 to "one"

        expect(pair)
            .first             // subject is now of type Int (actually 1)
            .toBeLessThan(2)   // subject is still of type Int (still 1)
            .toBeGreaterThan(0)

        fails {
            expect(pair)
                .first
                .toBeGreaterThan(2) // fails
                .toBeLessThan(0)    // not reported because `toBeGreaterThan` already fails
            //                         use `.first { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun first() {
        val pair = 1 to "one"

        expect(pair)
            .first { // subject inside this block is of type Int (actually 1)
                toBeLessThan(2)
            } // subject here is back to type Pair<Int, String>
            .first {
                toBeGreaterThan(0)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(pair)
                .first {
                    toBeGreaterThan(2) // fails
                    toBeLessThan(0)    // still evaluated even though `toBeGreaterThan` already fails
                    //                    use `.first.` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun secondFeature() {
        val pair = "one" to 1

        expect(pair)
            .second             // subject now of type Int (actually 1)
            .toBeLessThan(2)    // subject is still of type Int (still 1)
            .toBeGreaterThan(0)

        fails {
            expect(pair)
                .second
                .toBeGreaterThan(2) // fails
                .toBeLessThan(0)    // not reported because `toBeGreaterThan` already fails
            //                         use `.second { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun second() {
        val pair = "one" to 1

        expect(pair)
            .second { // subject inside this block is of type Int (actually 1)
                toBeLessThan(2)
            } // subject here is back to type Pair<Int, String>
            .second {
                toBeGreaterThan(0)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(pair)
                .second {
                    toBeGreaterThan(2) // fails
                    toBeLessThan(0)    // still evaluated even though `toBeGreaterThan` already fails,
                    //                    use `.second.` if you want a fail fast behaviour
                }
        }
    }
}
