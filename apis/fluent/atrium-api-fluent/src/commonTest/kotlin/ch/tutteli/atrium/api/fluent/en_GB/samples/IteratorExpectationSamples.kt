package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IteratorExpectationSamples {

    @Test
    fun toHaveNext() {
        val iterator = listOf(1).iterator()

        expect(iterator).toHaveNext()      // holds as iterator has a next element

        fails {
            iterator.next()                // returns the next element in iteration
            expect(iterator).toHaveNext()  // fails as list has only 1 element, i.e. no next any more.
        }
    }

    @Test
    fun notToHaveNext() {
        val iterator = listOf(1).iterator()

        fails {
            expect(iterator).notToHaveNext() // fails as iterator has a next element (has actually one element)
        }

        iterator.next()                      // returns the next element in iteration
        expect(iterator).notToHaveNext()     // does not fail as by now iterator has no next element
    }

    @Test
    fun nextFeature() {
        val iterator = listOf(1, 2, 3).iterator()

        expect(iterator)
            .next()                          // subject is now of type Int (actually 1)
            .toBeGreaterThan(0)
            .toBeLessThan(2)

        expect(iterator)
            .next()                         // subject is now of type Int (actually 2)
            .toBeGreaterThan(1)
            .toBeLessThan(3)

        fails {
            expect(iterator)
                .next()                     // subject is now of type Int (actually 3)
                .notToEqual(3)    // fails as subject is exactly 3
        }

        fails {
            expect(iterator)
                .next()                    // fails as list has only 3 elements
                .toEqual(4)      // not evaluated/reported because `next` already fails
        }
    }

    @Test
    fun next() {
        val iterator = listOf(1, 2, 3).iterator()

        expect(iterator)
            .next { // subject inside this expectation-group is of type Int (actually 1)
                toBeGreaterThan(0)
                toBeLessThan(2)
            } // subject here is back to type Iterator<Int>
            .next { // subject inside this expectation-group is of type Int (actually 2)
                toBeGreaterThan(1)
                toBeLessThan(3)
            }

        fails {
            expect(iterator)
                .next { // subject inside this expectation-group is of type Int (actually 3)
                    notToEqual(3) // fails as subject is exactly 3
                }
        }

        fails {
            expect(iterator).next { // fails as list has only 3 elements
                toEqual(4) // not evaluated/reported because `next` already fails
            }
        }
    }

}
