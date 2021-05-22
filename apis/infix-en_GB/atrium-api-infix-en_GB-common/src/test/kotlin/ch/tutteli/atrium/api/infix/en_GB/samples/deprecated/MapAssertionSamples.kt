package ch.tutteli.atrium.api.infix.en_GB.samples.deprecated

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapAssertionSamples {

    @Test
    fun containsBuilder() {
        expect(mapOf(1 to "a")) toContain (1 to "a")

        fails {
            // fails because the map does not contain Pair<1,"b">
            expect(mapOf(1 to "a")) toContain (1 to "b")
        }
    }

    @Test
    fun containsPair() {
        expect(mapOf(1 to "a")) toContain (1 to "a")

        fails {
            // fails because the map does not contain Pair<1,"b">
            expect(mapOf(1 to "a")) toContain (1 to "b")
        }
    }

    @Test
    fun containsOnlyPair() {
        expect(mapOf(1 to "a")) toContainOnly (1 to "a")

        fails {
            // fails because the map also contains Pair<1,"b">
            expect(mapOf(1 to "a", 1 to "b")) toContainOnly (1 to "a")
        }
    }

    @Test
    fun containsKeyValue() {
        // subject inside this block is of type String (actually "a")
        expect(mapOf(1 to "a")) toContain keyValues(keyValue(1) { this toEqual "a" })

        fails {
            // subject inside this block is of type String (actually "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")) toContain keyValues(keyValue(1) { this toEqual "b" })
        }
    }

    @Test
    fun containsOnlyKeyValue() {
        // subject inside this block is of type String (actually "a")
        expect(mapOf(1 to "a")) toContainOnly keyValues(keyValue(1) { this toEqual "a" })

        fails {
            // subject inside this block is of type String (actually "a")
            // fails because the map also contains Pair<1,"b">
            expect(mapOf(1 to "b")) toContainOnly keyValues(keyValue(1) { this toEqual "a" })
        }
    }

    @Test
    fun containsEntriesOf() {
        expect(mapOf(1 to "a")) toContainEntriesOf listOf(1 to "a")

        fails {
            expect(mapOf(1 to "a")) toContainEntriesOf listOf(1 to "b") // fails because the map does not contain <1,"b">
        }
    }

    @Test
    fun containsOnlyEntriesOf() {
        expect(mapOf(1 to "a")).toContainOnlyEntriesOf(listOf(1 to "a"))

        fails {
            // fails because the map does not contain only <1,"a">
            expect(mapOf(1 to "a", 1 to "b")) toContainOnlyEntriesOf listOf(1 to "a")
        }
    }

    @Test
    fun containsKey() {
        expect(mapOf(1 to "a")) toContainKey 1

        fails {
            expect(mapOf(1 to "a")) toContainKey 2 // fails because the map does not contain a key that equals 2
        }
    }

    @Test
    fun containsNotKey() {
        expect(mapOf(1 to "a")) notToContainKey 2

        fails {
            expect(mapOf(1 to "a")) notToContainKey 1   // fails because the map contains key that equals 1
        }
    }

    @Test
    fun getExistingFeature() {
        // subject is of type String (actually "a")
        expect(mapOf(1 to "a")) getExisting 1 toEqual ("a")

        fails {
            // subject is of type String (actually "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")) getExisting 1 toEqual "b"
        }

        fails {
            // subject is of type String, but assertion fails because key 2 does not exist
            expect(mapOf(1 to "a")) getExisting 2 toEqual "a"
        }
    }

    @Test
    fun getExisting() {
        // subject inside this block is of type String (actually "a")
        expect(mapOf(1 to "a")) getExisting key(1) { toEqual("a") }

        fails {
            // subject inside this block is of type String (actually "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")) getExisting key(1) { toEqual("b") }
        }

        fails {
            // subject is of type String, but assertion fails because key 2 does not exist
            expect(mapOf(1 to "a")) getExisting key(2) { toEqual("a") }
        }
    }

    @Test
    fun keysFeature() {
        //subject is of type Set<Int> (actually <1>)
        expect(mapOf(1 to "a")).keys toContain {
            // subject inside this block is of type Int
            this toEqual 1
        }

        fails {
            //subject is of type Set<Int> (actually <1>)
            expect(mapOf(1 to "a")).keys toContain { // subject inside this block is of type Int
                this toEqual 2 // fails because 1 is not equal to 2
            }
        }
    }

    @Test
    fun keys() {
        expect(mapOf(1 to "a")) keys { // subject inside this block is of type Set<Int> (actually <1>)
            this toEqual setOf(1)
        }

        fails {
            expect(mapOf(1 to "a")) keys { // subject inside this block is of type Set<Int> (actually <1>)
                this toEqual setOf(2)  // fails because <1> is not equal to <2>
            }
        }
    }

    @Test
    fun valuesFeature() {
        // subject inside this block is of type String
        expect(mapOf(1 to "a")).values toContain { //subject is of type Collection<String> (actually <"a">)
            // subject inside this block is of type String
            this toEqual "a"
        }

        fails {
            // subject inside this block is of type String
            expect(   // fails because "a" is not equal to "b"
                mapOf(1 to "a")
            )
                .values //subject is of type Collection<String> (actually <"a">)
                .toContain {
                    // subject inside this block is of type String
                    this toEqual "b"   // fails because "a" is not equal to "b"
                }
        }
    }

    @Test
    fun values() {
        expect(mapOf(1 to "a")) values {   // subject inside this block is of type Collection<String> (actually <"a">)
            this toEqual setOf("a")
        }

        fails {
            expect(mapOf(1 to "a")) values {   // subject inside this block is of type Collection<String> (actually <"a">)
                this toEqual setOf("b")    // fails because <"a"> is not equal to <"b">
            }
        }
    }

    @Test
    fun asEntriesFeature() {
        // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
        expect(mapOf(1 to "a")) asEntries o toContain {
            // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
            this toEqual mapOf(1 to "a").entries.first()
        }

        fails {
            // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
            expect(
                mapOf(
                    1 to "a"
                )   // fails because <1,"a"> is not equal to <1,"b">
            ) asEntries (o) toContain {
                // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
                this toEqual mapOf(1 to "b").entries.first()   // fails because <1,"a"> is not equal to <1,"b">
            }
        }
    }

    @Test
    fun asEntries() {
        expect(mapOf(1 to "a")) asEntries { // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
            this toEqual mapOf(1 to "a").entries
        }

        fails {
            expect(mapOf(1 to "a")) asEntries { // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
                this toEqual mapOf(1 to "b").entries   // fails because <1,"a"> is not equal to <1,"b">
            }
        }
    }

    @Test
    fun isEmpty() {
        expect(emptyMap<Int, String>()) toBe empty

        fails {
            expect(mapOf(1 to "a")) toBe empty  //fails because the map is not empty
        }
    }

    @Test
    fun isNotEmpty() {
        expect(mapOf(1 to "a")) notToBe empty

        fails {
            expect(emptyMap<Int, String>()) notToBe empty   //fails because the map is empty
        }
    }
}
