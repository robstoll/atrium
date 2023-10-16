package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInAnyOrderOnlyCreatorSamples {

    @Test
    fun entry() {
        expect(mapOf(1 to "a")) toContain o inAny order but only entry (1 to "a")

        fails { // because subject has additional entries
            expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order but only entry (2 to "c")
        }
    }

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order but only the pairs(
            2 to "b", 1 to "a"
        )

        fails { // because subject has additional entries
            expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order but only the pairs(1 to "a")
        }
    }

    @Test
    fun entryKeyValue() {
        expect(mapOf(1 to "apple")) toContain o inAny order but only entry(
            keyValue(1) { this toStartWith "a"  }
        )

        fails { // because subject has additional entries
            expect(mapOf(1 to "apple", 2 to "banana")) toContain o inAny order but only entry(
                keyValue(1) { this toStartWith "a" }
            )
        }
    }

    @Test
    fun entriesKeyValue() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order but only the entries(
            keyValue(2) { this toEqual "b" },
            keyValue(1) { this toEqual "a" },
        )

        fails { // because subject has additional entries
            expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order but only the entries(
                keyValue(1) { this toEqual "a" }
            )
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order but only entriesOf mapOf(2 to "b", 1 to "a")

        fails { // because subject has additional entries
            expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order but only entriesOf mapOf(1 to "a")
        }
    }
}
