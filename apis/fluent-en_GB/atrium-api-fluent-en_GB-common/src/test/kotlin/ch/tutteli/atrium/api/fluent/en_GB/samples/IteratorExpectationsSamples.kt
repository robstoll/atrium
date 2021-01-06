package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.hasNext
import ch.tutteli.atrium.api.fluent.en_GB.hasNotNext
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IteratorExpectationsSamples {

    @Test
    fun hasNext() {
        val list = listOf(1)
        val iterator = list.iterator()
        expect(iterator).hasNext()      // holds as iterator has a next element

        fails {
            iterator.next()             // returns the next element in iteration
            expect(iterator).hasNext()  // fails as list has only 1 element, i.e. no next any more.
        }
    }

    @Test
    fun hasNoNext() {
        val list = listOf(1)
        val iterator = list.iterator()     // fails as iterator has a next element (has actually one element)

        fails {
            expect(iterator).hasNotNext()
        }

        iterator.next()                    // returns the next element in iteration
        expect(iterator).hasNotNext()      // returns the next element in iteration
    }
}
