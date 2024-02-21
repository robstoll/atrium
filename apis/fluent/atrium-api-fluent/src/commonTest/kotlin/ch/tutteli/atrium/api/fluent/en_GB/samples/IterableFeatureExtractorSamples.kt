package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.max
import ch.tutteli.atrium.api.fluent.en_GB.min
import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThanOrEqualTo
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableFeatureExtractorSamples {
    @Test
    fun minFeature() {
        val iterable = sequenceOf(1, -2, 3).asIterable()

        expect(iterable).min().toEqual(-2) // subject is -2

        expect(iterable).min()
            .toBeLessThan(0) // subject is -2
            .toBeGreaterThan(-10) // subject is still -2

        fails {
            expect(iterable).min() // subject is -2
                .toBeLessThan(-5) // fails
                .toBeGreaterThan(-10) // skipped because previous expectation failed
        }
    }

    @Test
    fun min() {
        val iterable = sequenceOf(1, -2, 3).asIterable()

        expect(iterable).min { // subject is -2
            toBeLessThan(0) // subject is still -2
            toBeGreaterThanOrEqualTo(-2) // subject is still -2
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(iterable).min {// subject within this expectation-group is of type Int (actually -2)
                toBeLessThan(-2) // fails
                toEqual(-10) // still evaluated, even though  `toBeLessThan` already fails,
                //                      use `.min.` if you want a fail fast behaviour
            } // subject here is back type Iterable
        }
    }

    @Test
    fun maxFeature() {
        val iterable = sequenceOf(1, 2, 0).asIterable()

        expect(iterable).max().toEqual(2) // subject is 2

        expect(iterable).max()
            .toBeLessThan(10) // subject is 2
            .toBeGreaterThan(1) // subject is still 2

        fails {
            expect(iterable).max() // subject is 2
                .toBeLessThan(2) // fails
                .toEqual(1) // skipped because previous expectation failed
        }
    }

    @Test
    fun max() {
        val iterable = sequenceOf(1, 2, 0).asIterable()

        expect(iterable).max {
            toBeGreaterThan(0) // subject is 2
            toBeGreaterThanOrEqualTo(2) // subject is still 2
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(iterable).max {
                toBeGreaterThan(10) // fails
                toEqual(2) // still evaluated, even though  `toBeGreaterThan` already fails,
                //                      use `.max.` if you want a fail fast behaviour
            } // subject here is back type Iterable
        }
    }
}
