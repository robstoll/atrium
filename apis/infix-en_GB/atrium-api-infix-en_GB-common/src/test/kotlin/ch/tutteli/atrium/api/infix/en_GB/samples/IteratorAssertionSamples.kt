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
        expect(iterator) has next

        fails {
            iterator.next()
            expect(iterator) has next
        }
    }

    @Test
    fun hasNot() {
        val list = listOf(1)
        val iterator = list.iterator()

        fails {
            expect(iterator) hasNot next
        }

        iterator.next()
        expect(iterator) hasNot next
    }
}
