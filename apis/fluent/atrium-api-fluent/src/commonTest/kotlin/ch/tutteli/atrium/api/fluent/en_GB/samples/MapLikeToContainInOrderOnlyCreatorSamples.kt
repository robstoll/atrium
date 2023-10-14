package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInOrderOnlyCreatorSamples {

    @Test
    fun entry() {
        expect(mapOf(1 to "a")).toContain.inOrder.only.entry(1 to "a")

        fails { // because the entry 1="a" (which exists in the subject) is not the only entry
            expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entry(1 to "a")
        }
    }

    @Test
    fun entryKeyValue() {
        expect(mapOf(1 to "apple")).toContain.inOrder.only.entry(
            KeyValue(1) { toStartWith("a") },
        )

        fails { // because the entry 2="b" (which exists in the subject) is not the only entry
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inOrder.only.entry(
                KeyValue(2) { toStartWith("b") },
            )
        }
    }

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entries(
            1 to "a", 2 to "b"
        )

        fails { // because the pair entries (which all exist in the subject) do not have the same order
            expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entries(
                2 to "b",
                1 to "a",
            )
        }
    }

    @Test
    fun entriesKeyValue() {
        expect(mapOf(1 to "apple", 2 to "banana")).toContain.inOrder.only.entries(
            KeyValue(1) { toStartWith("a") },
            KeyValue(2) { toStartWith("b") },
        )

        fails { // because the key-value entries (which all exist in the subject) do not have the same order
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inOrder.only.entries(
                KeyValue(2) { toStartWith("b") },
                KeyValue(1) { toStartWith("a") },
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entriesOf(
            mapOf(1 to "a", 2 to "b")
        )

        fails { // because the map entries (which all exist in the subject) do not have the same order
            expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entriesOf(
                mapOf(
                    2 to "b",
                    1 to "a",
                )
            )
        }
    }
}
