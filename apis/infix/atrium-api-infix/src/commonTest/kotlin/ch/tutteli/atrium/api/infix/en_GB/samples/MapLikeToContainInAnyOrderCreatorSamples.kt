package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapLikeToContainInAnyOrderCreatorSamples {

    @Test
    fun entry() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order entry (2 to "b")

        fails { // because the value "b" of key 2 (which exists in the subject) is not equal to "c"
            expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order entry (2 to "c")
        }
    }

    @Test
    fun entryKeyValue() {
        expect(mapOf(1 to "apple", 2 to "banana")) toContain o inAny order entry (
            keyValue(2) { this toStartWith "b" }
        )

        fails { // because the value ("apple") of key 1 (which exists in the subject) does not start with "b"
            expect(mapOf(1 to "apple", 2 to "banana")) toContain o inAny order entry (
                keyValue(1) { this toStartWith "b" }
            )
        }
    }

    @Test
    fun entries() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order the entries(keyValue(2) { this toEqual "b" })
        
        fails { // because the value ("b") of key 1 (which exists in the subject) is not "b"
            expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order the entries(keyValue(1) { this toEqual "b" })
        }
    }

    @Test
    fun entriesOf() {
        expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order entriesOf mapOf(2 to "b")

        fails { // because the value ("a") of key 1 (which exists in the subject) is not "b"
            expect(mapOf(1 to "a", 2 to "b")) toContain o inAny order entriesOf mapOf(1 to "b")
        }
    }
}
