package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapExpectationSamples {

    @Test
    fun toContainBuilder() {
        expect(mapOf(1 to "a")).toContain.inAnyOrder.entries(1 to "a")

        fails { // because the map does not contain key 1 with value "b"
            expect(mapOf(1 to "a")).toContain.inAnyOrder.entries(1 to "b")
        }
    }

    @Test
    fun toContainPair() {
        expect(mapOf(1 to "a")).toContain(1 to "a")

        fails { // because the map does not contain key 1 with value "b"
            expect(mapOf(1 to "a")).toContain(1 to "b")
        }
    }

    @Test
    fun toContainOnlyPair() {
        expect(mapOf(1 to "a")).toContainOnly(1 to "a")

        fails { // because the map contains key 2 with value "b" in addition
            expect(mapOf(1 to "a", 2 to "b")).toContainOnly(1 to "a")
        }
    }

    @Test
    fun toContainKeyValue() {
        expect(mapOf(1 to "a"))
            .toContain(KeyValue(1) { // subject inside this block is of type String (actually "a")
                toEqual("a")
            })

        fails {
            expect(mapOf(1 to "a"))
                .toContain(KeyValue(1) {  // subject inside this block is of type String (actually "a")
                    toEqual("b")          // fails because "a" is not equal to "b"
                })
        }
    }

    @Test
    fun toContainOnlyKeyValue() {
        expect(mapOf(1 to "a"))
            .toContainOnly(KeyValue(1) { // subject inside this block is of type String (actually "a")
                toEqual("a")
            })

        fails {
            expect(mapOf(1 to "a", 2 to "b"))
                .toContainOnly(KeyValue(1) {  // subject inside this block is of type String (actually "a")
                    toEqual("a")
                })                            // fails because the map contains key 2 with value "b" in addition
        }
    }

    @Test
    fun toContainEntriesOf() {
        expect(mapOf(1 to "a")).toContainEntriesOf(mapOf(1 to "a"))

        fails { // because the map does not contain entry with key 1 and value "b"
            expect(mapOf(1 to "a")).toContainEntriesOf(mapOf(1 to "b"))
        }
    }

    @Test
    fun toContainOnlyEntriesOf() {
        expect(mapOf(1 to "a")).toContainOnlyEntriesOf(mapOf(1 to "a"))

        fails { // because the map contains key 2 with value "b" in addition
            expect(mapOf(1 to "a", 2 to "b"))
                .toContainOnlyEntriesOf(mapOf(1 to "a"))
        }
    }

    @Test
    fun toContainKey() {
        expect(mapOf(1 to "a")).toContainKey(1)

        fails { // because the map does not contain a key that equals 2
            expect(mapOf(1 to "a")).toContainKey(2)
        }
    }

    @Test
    fun notToContainKey() {
        expect(mapOf(1 to "a")).notToContainKey(2)

        fails { // because the map contains a key which equals 1
            expect(mapOf(1 to "a")).notToContainKey(1)
        }
    }

    @Test
    fun getExistingFeature() {
        expect(mapOf(1 to "a"))
            .getExisting(1) // subject is of type String (actually "a")
            .toEqual("a")

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(1)  // subject is of type String (actually "a")
                .toEqual("b")    // fails because "a" is not equal to "b"
        }

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(2) // expectation fails because key 2 does not exist
                .toEqual("a")   // not reported, use `getExisting(key) { ... }`
            //                     if you want that all expectations are evaluated
        }
    }

    @Test
    fun getExisting() {
        expect(mapOf(1 to "a"))
            .getExisting(1) {   // subject inside this block is of type String (actually "a")
                toEqual("a")
            }

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(1) {  // subject inside this block is of type String (actually "a")
                    toEqual("b")   // fails because "a" is not equal to "b"
                }
        }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(mapOf(1 to "a"))
                .getExisting(2) {   // fails because key 2 does not exist
                    toEqual("a")    // still evaluated because we use an expectation group block
                    //                 use `.getExisting(key).` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun keysFeature() {
        expect(mapOf(1 to "a"))
            .keys         // subject is now of type Set<Int> (containing 1)
            .toContain(1)

        fails {
            expect(mapOf(1 to "a"))
                .keys          // subject is now of type Set<Int> (containing 1)
                .toContain(2)  // fails because 1 is not equal to 2
        }
    }

    @Test
    fun keys() {
        expect(mapOf(1 to "a"))
            .keys { // subject inside this block is of type Set<Int> (containing 1)
                toEqual(setOf(1))
            }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(mapOf(1 to "a"))
                .keys {                // subject inside this block is of type Set<Int> (containing 1)
                    toEqual(setOf(2))  // fails because 1 is not equal to 2
                    toHaveSize(3)      // still evaluated because we use an expectation group block
                    //                    use `.keys.` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun valuesFeature() {
        expect(mapOf(1 to "a"))
            .values // subject is now of type Collection<String> (containing "a")
            .toContain("a")

        fails {
            expect(mapOf(1 to "a"))
                .values          // subject is now of type Collection<String> (containing "a")
                .toContain("b")  // fails because "a" is not equal to "b"
        }
    }

    @Test
    fun values() {
        expect(mapOf(1 to "a"))
            .values {   // subject inside this block is of type Collection<String> (containing "a")
                toEqual(setOf("a"))
            }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(mapOf(1 to "a"))
                .values {                   // subject inside this block is of type Collection<String> (containing <"a">)
                    toEqual(setOf("b"))    // fails because "a" is not equal to "b"
                    //                         use `.values.` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun asEntriesFeature() {
        expect(mapOf(1 to "a")).asEntries()
            .toEqual(mapOf(1 to "a").entries)

        fails { // because <1,"a"> is not equal to <1,"b">
            expect(mapOf(1 to "a")).asEntries()
                .toEqual(mapOf(1 to "b").entries)
        }
    }

    @Test
    fun asEntries() {
        expect(mapOf(1 to "a")).asEntries { // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
            toEqual(mapOf(1 to "a").entries)
        }

        fails {
            expect(mapOf(1 to "a")).asEntries {   // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
                toEqual(mapOf(1 to "b").entries)  // fails because <1,"a"> is not equal to <1,"b">
            }
        }
    }

    @Test
    fun toBeEmpty() {
        expect(emptyMap<Int, String>()).toBeEmpty()

        fails { // because the map is not empty
            expect(mapOf(1 to "a")).toBeEmpty()
        }
    }

    @Test
    fun notToBeEmpty() {
        expect(mapOf(1 to "a")).notToBeEmpty()

        fails { // because the map is empty
            expect(emptyMap<Int, String>()).notToBeEmpty()
        }
    }
}
