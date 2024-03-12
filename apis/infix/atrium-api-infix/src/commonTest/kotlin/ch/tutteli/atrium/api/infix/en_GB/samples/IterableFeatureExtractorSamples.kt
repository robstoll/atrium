package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.last
import ch.tutteli.atrium.api.infix.en_GB.max
import ch.tutteli.atrium.api.infix.en_GB.min
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.infix.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.infix.en_GB.toBeLessThan
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableFeatureExtractorSamples {
    @Test
    fun minFeature() {
        val iterable = sequenceOf(1, -2, 3).asIterable()

        expect(iterable) min o toEqual -2 // subject is -2

        expect(iterable) min o toBeLessThan 0 toBeGreaterThan -10 // subject is -2 and passes all expectations

        fails {
            expect(iterable) min o toBeLessThan -5 toBeGreaterThan -10 // subject is -2 and fails the first expectation,
        }
    }

    @Test
    fun min() {
        val iterable = sequenceOf(1, -2, 3).asIterable()

        expect(iterable) min {
            it toBeLessThan 0 toBeGreaterThanOrEqualTo -2 // subject is -2
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(iterable) min { // subject within this expectation-group is of type Int (actually -2)
                it toBeLessThan -2 // fails
                it toEqual -10 // still evaluated, even though  `toBeLessThan` already fails,
                //                use `it toBeLessThan -2 toEqual -10 ` if you want a fail fast behaviour
            } // subject here is back type Iterable
        }
    }

    @Test
    fun maxFeature() {
        val iterable = sequenceOf(1, 2, 0).asIterable()

        expect(iterable) max o toEqual 2 // subject is 2

        expect(iterable) max o toBeLessThan 10 toBeGreaterThan 1 // subject is 2 and passes all expectations

        fails {
            expect(iterable) max o toBeLessThan 2 toBeGreaterThan 0 // subject is 2 and fails the first expectation, the second is not evaluated
        }
    }

    @Test
    fun max() {
        val iterable = sequenceOf(1, 2, 0).asIterable()

        expect(iterable) max {
            it toBeGreaterThan 0 toBeGreaterThanOrEqualTo 2 // subject is 2 and passes all expectations
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(iterable) max { // subject within this expectation-group is of type Int (actually 2)
                it toBeGreaterThan 10 // fails
                it toEqual 2 // still evaluated, even though  `toBeGreaterThan` already fails,
                //              use `it toBeGreaterThan 10 toEqual 2` if you want a fail fast behaviour
            } // subject here is back type Iterable
        }
    }

    @Test
    fun lastFeature() {
        val iterable = sequenceOf(1, 2, 3).asIterable()

        expect(iterable) last o toEqual 3 // subject is 3

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
        val iterable = listOf(1, 2, 3).asIterable()

        expect(iterable) last {
            it toEqual 3 // subject is 3
        } last {
            it toBeGreaterThan(2)
            it toBeLessThan(4) // subject is 3 and passes all expectations
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(iterable).last { // subject within this expectation-group is of type Int (actually 3)
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
