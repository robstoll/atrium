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
        expect(iterable).min().toEqual(-2)
        expect(iterable).min().toBeLessThan(0).toBeGreaterThan(-10)
        fails {
            expect(iterable).min().toEqual(1)
        }
    }

    @Test
    fun min() {
        val iterable = sequenceOf(1, -2, 3).asIterable()
        expect(iterable).min {
            toBeLessThan(0)
            toBeGreaterThanOrEqualTo(-2)
        }
        fails {
            expect(iterable).min {
                toBeLessThan(-2)
                toEqual(-10)
            }
        }
    }

    @Test
    fun maxFeature() {
        val iterable = sequenceOf(1, 2, 0).asIterable()
        expect(iterable).max().toEqual(2)
        expect(iterable).max().toBeLessThan(10).toBeGreaterThan(1)
        fails {
            expect(iterable).max().toEqual(1)
        }
    }

    @Test
    fun max() {
        val iterable = sequenceOf(1, 2, 0).asIterable()
        expect(iterable).max {
            toBeGreaterThan(0)
            toBeGreaterThanOrEqualTo(2)
        }
        fails {
            expect(iterable).max {
                toBeGreaterThan(10)
                toEqual(2)
            }
        }
    }
}
