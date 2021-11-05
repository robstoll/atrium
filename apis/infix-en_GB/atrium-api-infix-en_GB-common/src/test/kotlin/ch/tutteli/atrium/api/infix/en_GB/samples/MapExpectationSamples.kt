package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapExpectationSamples {

    @Test
    fun toContainBuilder() {
        expect(mapOf(1 to "a")) toContain o inAny order entry (1 to "a")

        fails { // because the map does not contain key 1 with value "b"
            expect(mapOf(1 to "a")) toContain o inAny order entry (1 to "b")
        }
    }

    @Test
    fun toContainPair() {
        expect(mapOf(1 to "a")) toContain (1 to "a")

        fails { // because the map does not contain key 1 with value "b"
            expect(mapOf(1 to "a")) toContain (1 to "b")
        }
    }

    @Test
    fun toContainOnlyPair() {
        expect(mapOf(1 to "a")) toContainOnly (1 to "a")

        fails { // because the map contains key 2 with value "b" in addition
            expect(mapOf(1 to "a", 2 to "b")) toContainOnly (1 to "a")
        }
    }

    @Test
    fun toContainPairs() {
        expect(mapOf(1 to "a", 2 to "b")) toContain pairs(1 to "a", 2 to "b")

        fails { // because the map contains key 2 with value "b" and not key 1 with value "b"
            expect(mapOf(1 to "a", 2 to "b")) toContain pairs(1 to "a", 1 to "b")
        }
    }

    @Test
    fun toContainOnlyPairs() {
        expect(mapOf(1 to "a", 2 to "b")) toContainOnly pairs(1 to "a", 2 to "b")

        fails { // because the map contains key 2 with value "b" in addition
            expect(mapOf(1 to "a", 2 to "b")) toContainOnly pairs(1 to "a")
        }
    }

    @Test
    fun toContainKeyValue() {
        expect(mapOf(1 to "a")) toContain keyValue(1) { // subject inside this block is of type String (actually "a")
            this toEqual "a"
        }

        fails {
            expect(mapOf(1 to "a")) toContain keyValue(1) {  // subject inside this block is of type String (actually "a")
                this toEqual "b"          // fails because "a" is not equal to "b"
            }
        }
    }

    @Test
    fun toContainOnlyKeyValue() {
        expect(mapOf(1 to "a")) toContainOnly keyValue(1) { // subject inside this block is of type String (actually "a")
            this toEqual "a"
        }

        fails { // fails because the map contains key 2 with value "b" in addition
            expect(
                mapOf(1 to "a", 2 to "b")
            ) toContainOnly keyValue(1) {  // subject inside this block is of type String (actually "a")
                this toEqual "a"
            }
        }
    }

    @Test
    fun toContainKeyValues() {
        expect(mapOf(1 to "a")) toContain keyValues(
            keyValue(1) { // subject inside this block is of type String (actually "a")
                this toEqual "a"
            }
        )

        fails {
            expect(mapOf(1 to "a")) toContain keyValues(
                keyValue(1) { // subject inside this block is of type String (actually "a")
                    this toEqual "b"
                }
            )
        }
    }

    @Test
    fun toContainOnlyKeyValues() {
        expect(mapOf(1 to "a", 2 to "b")) toContainOnly keyValues(
            keyValue(1) { // subject inside this block is of type String (actually "a")
                this toEqual "a"
            },
            keyValue(2) { // subject inside this block is of type String (actually "b")
                this toEqual "b"
            }
        )

        fails {
            expect(mapOf(1 to "a", 2 to "b")) toContainOnly keyValues(
                keyValue(1) { // subject inside this block is of type String (actually "a")
                    this toEqual "a"
                }
            )
        }

    }

    @Test
    fun toContainEntriesOf() {
        expect(mapOf(1 to "a")) toContainEntriesOf mapOf(1 to "a")

        fails { // because the map does not contain entry with key 1 and value "b"
            expect(mapOf(1 to "a")) toContainEntriesOf mapOf(1 to "b")
        }
    }

    @Test
    fun toContainOnlyEntriesOf() {
        expect(mapOf(1 to "a")) toContainOnlyEntriesOf mapOf(1 to "a")

        fails { // because the map contains key 2 with value "b" in addition
            expect(mapOf(1 to "a", 2 to "b")) toContainOnlyEntriesOf mapOf(1 to "a")
        }
    }

    @Test
    fun toContainKey() {
        expect(mapOf(1 to "a")) toContainKey 1

        fails { // because the map does not contain a key that equals 2
            expect(mapOf(1 to "a")) toContainKey 2
        }
    }

    @Test
    fun notToContainKey() {
        expect(mapOf(1 to "a")) notToContainKey 2

        fails { // because the map contains a key which equals 1
            expect(mapOf(1 to "a")) notToContainKey 1
        }
    }

    @Test
    fun toBeEmpty() {
        expect(emptyMap<Int, String>()) toBe empty

        fails { // because the map is not empty
            expect(mapOf(1 to "a")) toBe empty
        }
    }

    @Test
    fun notToBeEmpty() {
        expect(mapOf(1 to "a")) notToBe empty

        fails { // because the map is empty
            expect(emptyMap<Int, String>()) notToBe empty
        }
    }
}
