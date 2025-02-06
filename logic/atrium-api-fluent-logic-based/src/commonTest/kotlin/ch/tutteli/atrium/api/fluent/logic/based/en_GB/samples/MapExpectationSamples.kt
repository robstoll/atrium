package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
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
            .toContain(KeyValue(1) { // subject inside this expectation-group is of type String (actually "a")
                toEqual("a")
            })

        fails {
            expect(mapOf(1 to "a"))
                .toContain(KeyValue(1) {  // subject inside this expectation-group is of type String (actually "a")
                    toEqual("b")          // fails because "a" is not equal to "b"
                })
        }
    }

    @Test
    fun toContainOnlyKeyValue() {
        expect(mapOf(1 to "a"))
            .toContainOnly(KeyValue(1) { // subject inside this expectation-group is of type String (actually "a")
                toEqual("a")
            })

        fails {
            expect(mapOf(1 to "a", 2 to "b"))
                .toContainOnly(KeyValue(1) {  // subject inside this expectation-group is of type String (actually "a")
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
