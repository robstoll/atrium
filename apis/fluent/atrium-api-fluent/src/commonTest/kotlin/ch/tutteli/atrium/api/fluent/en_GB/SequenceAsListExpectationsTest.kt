package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class SequenceAsListExpectationsTest {

    @Test
    fun containing_the_same_values_after_transforming_the_sequence_to_a_list() {
        val sequence = sequenceOf(1, 2, 3)
        expect(sequence).asList().toEqual(listOf(1, 2, 3))
    }

    @Test
    fun containing_the_same_values_after_transforming_the_sequence_to_a_list_using_the_expectationCreator() {
        val sequence = sequenceOf(1, 2, 3)
        expect(sequence).asList {
            toEqual(listOf(1, 2, 3))
        }
    }
}
