package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInAnyOrderCreatorSamples {

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entries(
            2 to "b"
        )
        
        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entries(
                1 to "b" // fails because subject does not have this entry
            )
        }
    }

    @Test
    fun entriesKeyValue() {
        expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.entries(
            KeyValue(2) { toStartWith("b") }
        )

        fails {
            expect(mapOf(1 to "apple", 2 to "banana")).toContain.inAnyOrder.entries(
                KeyValue(1) { toStartWith("b") } // fails because subject does not have this entry
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entriesOf(
            mapOf(
                2 to "b",
            )
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")).toContain.inAnyOrder.entriesOf(
                mapOf(1 to "b") // fails because subject does not have this entry
            )
        }
    }
}
