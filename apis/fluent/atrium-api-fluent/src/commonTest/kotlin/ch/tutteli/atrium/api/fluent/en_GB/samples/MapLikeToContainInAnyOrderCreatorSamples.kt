package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInAnyOrderCreatorSamples {

    @Test
    fun entry() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entry(2 to "b")

        fails { // because the value "b" of key 2 (which exists in the subject) is not equal to "c"
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entry(2 to "c")
        }
    }

    @Test
    fun entryKeyValue() {
        expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.entry(
            KeyValue(2) { toStartWith("b") }
        )

        fails { // because the value ("apple") of key 1 (which exists in the subject) does not start with "b"
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.entry(
                KeyValue(1) { toStartWith("b") }
            )
        }
    }

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entries(
            2 to "b"
        )
        
        fails { // because the value ("b") of key 1 (which exists in the subject) is not "b"
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entries(1 to "b")
        }
    }

    @Test
    fun entriesKeyValue() {
        expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.entries(
            KeyValue(2) { toStartWith("b") }
        )

        fails { // because the value ("apple") of key 1 (which exists in the subject) does not start with "b"
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.entries(
                KeyValue(1) { toStartWith("b") }
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entriesOf(mapOf(2 to "b"))

        fails { // because the value ("a") of key 1 (which exists in the subject) is not "b"
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entriesOf(mapOf(1 to "b"))
        }
    }
}
