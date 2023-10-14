package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInOrderOnlyCreatorSamples {
    
    @Test
    fun entry() {
        expect(mapOf(1 to "a")) toContain o inGiven order and only entry (1 to "a")

        fails { // because the entry 1="a" (which exists in the subject) is not the only entry
            expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only entry (1 to "a")
        }
    }

    @Test
    fun entryKeyValue() {
        expect(mapOf(1 to "apple")) toContain o inGiven order and only entry(
            keyValue(1) { this toStartWith "a" }
        )

        fails { // because the entry 2="b" (which exists in the subject) is not the only entry
            expect(mapOf(1 to "apple", 2 to "banana")) toContain o inGiven order and only entry(
                keyValue(2) { this toStartWith "b" }
            )
        }
    }

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only the entries(
            keyValue(1) { this toEqual "a" },
            keyValue(2) { this toEqual "b" },
        )

        fails { // because the pair entries (which all exist in the subject) do not have the same order
            expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only the entries(
                keyValue(2) { this toEqual "b" },
                keyValue(1) { this toEqual "a" },
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only entriesOf mapOf(1 to "a", 2 to "b")

        fails { // because the map entries (which all exist in the subject) do not have the same order
            expect(mapOf(1 to "a", 2 to "b")) toContain o inGiven order and only entriesOf mapOf(
                2 to "b",
                1 to "a",
            )
        }
    }
}
