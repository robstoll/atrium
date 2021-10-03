package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IteratorExpectationSamples {

    @Test
    fun toHave_next() {
        val list = listOf(1)
        val iterator = list.iterator()
        expect(iterator) toHave next      // holds as iterator has a next element

        fails {
            iterator.next()               // returns the next element in iteration
            expect(iterator) toHave next  // fails as list has only 1 element, i.e. no next any more.
        }
    }

    @Test
    fun notToHave_next() {
        val list = listOf(1)
        val iterator = list.iterator()

        fails {
            expect(iterator) notToHave next  // fails as iterator has a next element (has actually one element)
        }

        iterator.next()                      // returns the next element in iteration
        expect(iterator) notToHave next      // list has no more elements thus expectation holds
    }
}
