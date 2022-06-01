package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class SequenceSubjectChangerSamples {

    @Test
    fun asIterableFeature() {
        expect(sequenceOf(1, 2, 3))
            .asIterable() // subject is now of type Iterable<Int>
            .toContain
            .inOrder // order specifier
            .only
            .values(1, 2, 3)

        fails {
            expect(sequenceOf(1, 2, 3))
                .asIterable() // subject is now of type Iterable<Int>
                .toContain(4)
        }

    }

    @Test
    fun asIterable() {
        expect(sequenceOf(1, 2, 3))
            .asIterable { // subject within this expectation-group is of type Iterable<Int>
                toContain(1)
                toContain(2)
                toContain(3)
            } // subject here is back to type Sequence<Int>

        fails {
            expect(sequenceOf(1, 2, 3))
                .asIterable { // subject within this expectation-group is of type Iterable<Int>
                    toContain(4)
                } // subject here is back to type Sequence<Int>
        }
    }

    @Test
    fun asListFeature() {
        expect(sequenceOf(1, 2, 3))
            .asList() // subject is now of type List<Int>
            .toEqual(listOf(1, 2, 3))
    }

    @Test
    fun asList() {
        expect(sequenceOf(1, 2, 3))
            .asList { // subject within this expectation-group is of type List<Int>
                toEqual(listOf(1, 2, 3))
            } // subject here is back to type Sequence<Int>
    }
}
