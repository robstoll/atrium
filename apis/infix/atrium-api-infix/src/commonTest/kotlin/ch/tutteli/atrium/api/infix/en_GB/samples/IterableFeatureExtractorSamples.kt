package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.it
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
        expect(iterable) min o toEqual -2
        expect(iterable) min o toBeLessThan 0 toBeGreaterThan -10
        fails {
            expect(iterable) min o toEqual 1
        }
    }

    @Test
    fun min() {
        val iterable = sequenceOf(1, -2, 3).asIterable()
        expect(iterable) min {
            it toBeLessThan 0 toBeGreaterThanOrEqualTo -2
        }
        fails {
            expect(iterable) min {
                it toBeLessThan -2 toEqual -10
            }
        }
    }

    @Test
    fun maxFeature() {
        val iterable = sequenceOf(1, 2, 0).asIterable()
        expect(iterable) max o toEqual 2
        expect(iterable) max o toBeLessThan 10 toBeGreaterThan 1
        fails {
            expect(iterable) max o toEqual 1
        }
    }

    @Test
    fun max() {
        val iterable = sequenceOf(1, 2, 0).asIterable()
        expect(iterable) max {
            it toBeGreaterThan 0 toBeGreaterThanOrEqualTo 2
        }
        fails {
            expect(iterable) max {
                it toBeGreaterThan 10 toEqual 2
            }
        }
    }
}
