package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class SequenceSubjectChangerSamples {

    @Test
    fun asIterableFeature() {
        val sequence = sequenceOf(1, 2, 3)
        expect(sequence) asIterable o toContain o inGiven order and only the values(1, 2, 3)
        //                  |                       | order specifier
        //                  |  subject is now of type Iterable<Int>

        fails {
            expect(sequenceOf(1, 2, 3)) asIterable o toContain 4
            //                                      | subject is now of type Iterable<Int>
        }
    }

    @Test
    fun asIterable() {
        expect(sequenceOf(1, 2, 3))
            .asIterable { //subject within this expectation-group is of type Iterable<Int>
                it toContain 1
                it toContain 2
                it toContain 3
            } // subject is back to Sequence<Int>

        fails {
            expect(sequenceOf(1, 2, 3))
                .asIterable { //subject within this expectation-group is of type Iterable<Int>

                    it toContain 4
                } //subject here is back to type Sequence<Int>
        }
    }

    @Test
    fun asListFeature() {
        expect(sequenceOf(1, 2, 3)) asList o toEqual listOf(1, 2, 3)
        //                                      |subject is now of type List<Int>
    }

    @Test
    fun asList() {
        expect(sequenceOf(1, 2, 3))

            .asList {//subject within this expectation group is of type List<Int>
                toEqual(listOf(1, 2, 3))
            } //subject here is back to type to Sequence<Int>
    }
}



