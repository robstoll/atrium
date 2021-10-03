package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.notToHaveNext
import ch.tutteli.atrium.api.fluent.en_GB.toHaveNext
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IteratorExpectationSamples {

    @Test
    fun toHaveNext() {
        val list = listOf(1)
        val iterator = list.iterator()
        expect(iterator).toHaveNext()      // holds as iterator has a next element

        fails {
            iterator.next()                // returns the next element in iteration
            expect(iterator).toHaveNext()  // fails as list has only 1 element, i.e. no next any more.
        }
    }

    @Test
    fun notToHaveNext() {
        val list = listOf(1)
        val iterator = list.iterator()     // fails as iterator has a next element (has actually one element)

        fails {
            expect(iterator).notToHaveNext()
        }

        iterator.next()                    // returns the next element in iteration
        expect(iterator).notToHaveNext()   // returns the next element in iteration
    }
}
