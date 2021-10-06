package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class SequenceExpectationSamples {

    @Test
    fun asIterableFeature() {
        expect(sequenceOf(1, 2, 3))
            .asIterable()
            .toContain
            .inOrder // order specifier
            .only
            .values(1, 2, 3)

        fails {
            expect(sequenceOf(1, 2, 3))
                .asIterable()
                .toContain(4)
        }
    }

    @Test
    fun asIterable() {
        expect(sequenceOf(1, 2, 3))
            .asIterable {
                toContain(1)
                toContain(2)
                toContain(3)
            }

        fails {
            expect(sequenceOf(1, 2, 3))
                .asIterable {
                    toContain(4)
                }
        }
    }

    @Test
    fun asListFeature() {
        expect(sequenceOf(1, 2, 3))
            .asList()
            .toEqual(listOf(1, 2, 3))
    }

    @Test
    fun asList() {
        expect(sequenceOf(1, 2, 3))
            .asList {
                toEqual(listOf(1, 2, 3))
            }
    }
}
