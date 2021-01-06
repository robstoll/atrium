package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.has
import ch.tutteli.atrium.api.infix.en_GB.hasNot
import ch.tutteli.atrium.api.infix.en_GB.next
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IteratorAssertionSamples {

    @Test
    fun has_next() {
        val list = listOf(1)
        val iterator = list.iterator()
        expect(iterator) has next      // holds as iterator has a next element

        fails {
            iterator.next()            // returns the next element in iteration
            expect(iterator) has next  // fails as list has only 1 element, i.e. no next any more.
        }
    }

    @Test
    fun hasNot_next() {
        val list = listOf(1)
        val iterator = list.iterator()

        fails {
            expect(iterator) hasNot next  // fails as iterator has a next element (has actually one element)
        }

        iterator.next()                   // returns the next element in iteration
        expect(iterator) hasNot next      // list has no more elements thus expectation holds
    }
}
