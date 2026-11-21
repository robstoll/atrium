package ch.tutteli.atrium.api.fluent.en_GB

import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IterableAsListExpectationsTest {

    @Test
    fun containing_the_same_list_after_narrowing_down_the_type() {
        val list = listOf(1, 2, 3)
        expect(list as Iterable<Int>).asList().toEqual(list)
    }

    @Test
    fun containing_the_same_list_after_narrowing_down_the_type_using_the_expectationCreator() {
        val list = listOf(1, 2, 3)
        expect(list as Iterable<Int>).asList {
            toEqual(list)
        }
    }
}
