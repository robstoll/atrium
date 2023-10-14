package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInOrderOnlyCreatorSamples {

    @Test
    fun entry() {
        expect(mapOf(1 to "a")).toContain.inOrder.only.entry(1 to "a")

        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entry(
                1 to "a", // fails because subject does not have the same order
            )
        }
    }

    @Test
    fun entryKeyValue() {
        expect(mapOf(1 to "apple")).toContain.inOrder.only.entry(
            KeyValue(1) { toStartWith("a") },
        )

        fails {
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inOrder.only.entry(
                KeyValue(2) { toStartWith("b") }, // fails because subject has more entries
            )
        }
    }

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entries(
            1 to "a", 2 to "b"
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entries(
                2 to "b", // fails because subject does not have the same order
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

        fails {
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inOrder.only.entries(
                KeyValue(2) { toStartWith("b") }, // fails because subject does not have the same order
                KeyValue(1) { toStartWith("a") },
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entriesOf(
            mapOf(1 to "a", 2 to "b")
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inOrder.only.entriesOf(
                mapOf(
                    2 to "b", // fails because subject does not have the same order
                    1 to "a",
                )
            )
        }
    }
}
