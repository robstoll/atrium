package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapExpectationSamples {

    @Test
    fun toContainBuilder() {
        expect(mapOf(1 to "a")) toContain (1 to "a")

        fails { // because the map does not contain key 1 with value "b"
            expect(mapOf(1 to "a")) toContain (1 to "b")
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
    fun toContainKeyValue() {
        // subject inside this block is of type String (actually "a")
        expect(mapOf(1 to "a")) toContain keyValues(keyValue(1) { this toEqual "a" })

        fails {
            // subject inside this block is of type String (actually "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")) toContain keyValues(keyValue(1) { this toEqual "b" })
        }
    }

    @Test
    fun toContainOnlyKeyValue() {
        // subject inside this block is of type String (actually "a")
        expect(mapOf(1 to "a")) toContainOnly keyValues(keyValue(1) { this toEqual "a" })

        fails {
            // subject inside this block is of type String (actually "a")
            // fails because the map contains key 2 with value "b" in addition
            expect(mapOf(1 to "a", 2 to "b")) toContainOnly keyValues(keyValue(1) { this toEqual "a" })
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
    fun getExistingFeature() {
        // subject is of type String (actually "a")
        expect(mapOf(1 to "a")) getExisting 1 toEqual "a"

        fails {
            // subject is of type String (actually "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")) getExisting 1 toEqual "b"
        }

        fails {
            // expectation fails because key 2 does not exist
            // not reported, use `getExisting(key) { ... }`
            // if you want that all expectations are evaluated
            expect(mapOf(1 to "a")) getExisting 2 toEqual "a"
        }
    }

    @Test
    fun getExisting() {
        // subject inside this block is of type String (actually "a")
        expect(mapOf(1 to "a")) getExisting key(1) {
            this toEqual "a"
        }

        fails {
            // subject inside this block is of type String (actually "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")) getExisting key(1) {
                this toEqual "b"
            }
        }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            // fails because key 2 does not exist
            // still evaluated because we use an expectation group block
            // use `.getExisting(key).` if you want a fail fast behaviour

            expect(mapOf(1 to "a")) getExisting key(2) {
                this toEqual "a"
            }
        }
    }

    @Test
    fun keysFeature() {
        // subject is now of type Set<Int> (containing 1)
        expect(mapOf(1 to "a")) keys {
            this toContain 1
        }

        fails {
            // subject is now of type Set<Int> (containing 1)
            // fails because 1 is not equal to 2

            expect(mapOf(1 to "a")) keys {
                this toContain 2
            }
        }
    }

    @Test
    fun keys() {
        expect(mapOf(1 to "a")) keys { // subject inside this block is of type Set<Int> (containing 1)
            this toEqual setOf(1)
        }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(mapOf(1 to "a")) keys { // subject inside this block is of type Set<Int> (containing 1)
                toEqual(setOf(2))          // fails because 1 is not equal to 2
                toHaveSize(3)      // still evaluated because we use an expectation group block
                // use `.keys.` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun valuesFeature() {
        // subject is now of type Collection<String> (containing "a")
        expect(mapOf(1 to "a")) values {
            this toContain "a"
        }

        fails {
            // subject is now of type Collection<String> (containing "a")
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")) values {
                this toContain "b"
            }
        }
    }

    @Test
    fun values() {
        // subject inside this block is of type Collection<String> (containing "a")
        expect(mapOf(1 to "a")) values {
            this toEqual setOf("a")
        }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            // subject inside this block is of type Collection<String> (containing <"a">)
            // fails because "a" is not equal to "b"
            // use `.values.` if you want a fail fast behaviour
            expect(mapOf(1 to "a")) values {
                this toEqual setOf("b")
            }
        }
    }

    @Test
    fun asEntriesFeature() {
        expect(mapOf(1 to "a")) asEntries o toEqual (mapOf(1 to "a").entries)

        fails { // because <1,"a"> is not equal to <1,"b">
            expect(mapOf(1 to "a")) asEntries o toEqual (mapOf(1 to "b").entries)
        }
    }

    @Test
    fun asEntries() {
        expect(mapOf(1 to "a")) asEntries { // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
            this toEqual mapOf(1 to "a").entries
        }

        fails {
            expect(mapOf(1 to "a")) asEntries {   // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
                this toEqual mapOf(1 to "b").entries  // fails because <1,"a"> is not equal to <1,"b">
            }
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
