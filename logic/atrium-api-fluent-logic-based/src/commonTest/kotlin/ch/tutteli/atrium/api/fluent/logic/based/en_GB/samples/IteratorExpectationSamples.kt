package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
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

}
