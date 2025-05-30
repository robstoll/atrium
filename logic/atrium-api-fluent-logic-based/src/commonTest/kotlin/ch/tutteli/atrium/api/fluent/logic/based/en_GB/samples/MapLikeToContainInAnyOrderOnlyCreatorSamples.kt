package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInAnyOrderOnlyCreatorSamples {

    @Test
    fun entry() {
        expect(mapOf(1 to "a")).toContain.inAnyOrder.only.entry(1 to "a")

        fails { // because subject has additional entries
            expect(mapOf(1 to "a", 2 to "b"))
                .toContain.inAnyOrder.only.entry(1 to "a")
        }
    }

    @Test
    fun entryKeyValue() {
        expect(mapOf(1 to "apple")).toContain.inAnyOrder.only.entry(
            KeyValue(1) { toStartWith("a") },
        )

        fails { // because subject has additional entries
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.only.entry(
                KeyValue(1) { toStartWith("a") },
            )
        }
    }

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.only.entries(
            2 to "b", 1 to "a"
        )

        fails { // because subject has additional entries
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.only.entries(1 to "a")
        }
    }

    @Test
    fun entriesKeyValue() {
        expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.only.entries(
            KeyValue(2) { toStartWith("b") },
            KeyValue(1) { toStartWith("a") },
        )

        fails { // because subject has additional entries
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.only.entries(
                KeyValue(1) { toStartWith("a") },
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.only.entriesOf(
            mapOf(2 to "b", 1 to "a")
        )

        fails { // because subject has additional entries
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.only.entriesOf(
                mapOf(1 to "a")
            )
        }
    }
}
