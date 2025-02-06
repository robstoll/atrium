package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IteratorFeatureExtractorSamples {

    @Test
    fun nextFeature() {
        val iterator = listOf(1, 2, 3).iterator()

        expect(iterator)
            .next()                    // subject is now of type Int (actually 1)
            .toBeGreaterThan(0)
            .toBeLessThan(2)

        expect(iterator)
            .next()                    // subject is now of type Int (actually 2)
            .toBeGreaterThan(1)
            .toBeLessThan(3)

        fails {
            expect(iterator)
                .next()                // subject is now of type Int (actually 3)
                .notToEqual(3)         // fails as subject is exactly 3
        }

        fails {
            expect(iterator)
                .next()                // fails as list has only 3 elements
                .toEqual(4)            // not evaluated/reported because `next` already fails
            //                            use `.next { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun next() {
        val iterator = listOf(1, 2, 3).iterator()

        expect(iterator)
            .next {                    // subject inside this expectation-group is of type Int (actually 1)
                toBeGreaterThan(0)
                toBeLessThan(2)
            }                          // subject here is back to type Iterator<Int>
            .next {                    // subject inside this expectation-group is of type Int (actually 2)
                toBeGreaterThan(1)
                toBeLessThan(3)
            }

        fails {
            expect(iterator)
                .next {                // subject inside this expectation-group is of type Int (actually 3)
                    notToEqual(3)      // fails as subject is exactly 3
                }
        }

        fails {
            expect(iterator).next {    // fails as list has only 3 elements
                toEqual(4)             // not evaluated/reported because `next` already fails
            //                            use `.next()` if you want a fail fast behaviour
            }
        }
    }

}
