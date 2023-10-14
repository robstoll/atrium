package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInOrderOnlyCreatorSamples {
    
    @Test
    fun entry() {
        expect(mapOf(1 to "a")) toContain o inGiven order and only entry (1 to "a")

        fails {
            expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only entry (1 to "a") // fails because subject does not have the same order
        }
    }

    @Test
    fun entryKeyValue() {
        expect(mapOf(1 to "apple")) toContain o inGiven order and only entry(
            keyValue(1) { this toStartWith "a" }
        )

        fails {
            expect(mapOf(1 to "apple", 2 to "banana")) toContain o inGiven order and only entry(
                keyValue(2) { this toStartWith "b" } // fails because subject has more entries
            )
        }
    }

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only the entries(
            keyValue(1) { this toEqual "a" },
            keyValue(2) { this toEqual "b" },
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only the entries(
                keyValue(2) { this toEqual "b" }, // fails because subject does not have the same order
                keyValue(1) { this toEqual "a" },
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only entriesOf mapOf(1 to "a", 2 to "b")

        fails {
            expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only entriesOf mapOf(
                2 to "b", // fails because subject does not have the same order
                1 to "a",
            )
        }
    }
}