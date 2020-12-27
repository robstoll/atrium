package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.has
import ch.tutteli.atrium.api.infix.en_GB.hasNot
import ch.tutteli.atrium.api.infix.en_GB.next
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IteratorAssertionSamples {

    @Test
    fun has() {
        val list = listOf(1)
        val iterator = list.iterator()
        expect(iterator) has next  //subject is iterator of list

        fails {
            iterator.next()  //returns the next element in iteration
            expect(iterator) has next  //fails as list has only 1 element
        }
    }

    @Test
    fun hasNot() {
        val list = listOf(1)
        val iterator = list.iterator()

        fails {
            expect(iterator) hasNot next  //fails as list has one element
        }

        iterator.next()  //returns the next element in iteration
        expect(iterator) hasNot next   //list has no more elements
    }
}
