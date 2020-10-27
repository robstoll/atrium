package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.hasNext
import ch.tutteli.atrium.api.fluent.en_GB.hasNotNext
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IteratorAssertionSamples {

    @Test
    fun hasNext() {
        val list = listOf(1)
        val iterator = list.iterator()
        expect(iterator).hasNext()

        fails {
            iterator.next()
            expect(iterator).hasNext()
        }
    }

    @Test
    fun hasNoNext() {
        val list = listOf(1)
        val iterator = list.iterator()

        fails {
            expect(iterator).hasNotNext()
        }

        iterator.next()
        expect(iterator).hasNotNext()
    }
}
