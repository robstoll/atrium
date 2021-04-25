//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.creating.Expect
import kotlin.test.Test

class MapAssertionSamples {

    @Test
    fun containsBuilder() {
        expect(mapOf(1 to "a")).contains.inAnyOrder.entries(1 to "a")

        fails {
            expect(mapOf(1 to "a")).contains.inAnyOrder
                .entries(1 to "b")   // fails because the map does not contain Pair<1,"b">
        }
    }

    @Test
    fun containsPair() {
        expect(mapOf(1 to "a")).contains(1 to "a")

        fails {
            expect(mapOf(1 to "a"))
                .contains(1 to "b") // fails because the map does not contain Pair<1,"b">
        }
    }

    @Test
    fun containsOnlyPair() {
        expect(mapOf(1 to "a")).containsOnly(1 to "a")

        fails {
            expect(mapOf(1 to "a", 1 to "b"))
                .containsOnly(1 to "a") // fails because the map also contains Pair<1,"b">
        }
    }

    @Test
    fun containsKeyValue() {
        expect(mapOf(1 to "a"))
            .contains(
                KeyValue(1, { // subject inside this block is of type String (actually "a")
                    toBe("a")
                })
            )

        fails {
            expect(mapOf(1 to "a"))
                .contains(
                    KeyValue(1, {   // subject inside this block is of type String (actually "a")
                        toBe("b")   // fails because "a" is not equal to "b"
                    })
                )
        }
    }

    @Test
    fun containsOnlyKeyValue() {
        expect(mapOf(1 to "a"))
            .containsOnly(
                KeyValue(1, {   // subject inside this block is of type String (actually "a")
                    toBe("a")
                })
            )

        fails {
            expect(mapOf(1 to "a", 1 to "b"))
                .containsOnly(
                    KeyValue(1, {   // subject inside this block is of type String (actually "a")
                        toBe("a")   // fails because the map also contains Pair<1,"b">
                    })
                )
        }
    }

    @Test
    fun containsEntriesOf() {
        expect(mapOf(1 to "a")).containsEntriesOf(listOf(1 to "a"))

        fails {
            expect(mapOf(1 to "a")).containsEntriesOf(listOf(1 to "b")) // fails because the map does not contain <1,"b">
        }
    }

    @Test
    fun containsOnlyEntriesOf() {
        expect(mapOf(1 to "a")).containsOnlyEntriesOf(listOf(1 to "a"))

        fails {
            expect(mapOf(1 to "a", 1 to "b"))
                .containsOnlyEntriesOf(listOf(1 to "a")) // fails because the map does not contain only <1,"a">
        }
    }

    @Test
    fun containsKey() {
        expect(mapOf(1 to "a")).containsKey(1)

        fails {
            expect(mapOf(1 to "a")).containsKey(2) // fails because the map does not contain a key that equals 2
        }
    }

    @Test
    fun containsNotKey() {
        expect(mapOf(1 to "a")).containsNotKey(2)

        fails {
            expect(mapOf(1 to "a")).containsNotKey(1)   // fails because the map contains key that equals 1
        }
    }

    @Test
    fun getExistingFeature() {
        expect(mapOf(1 to "a"))
            .getExisting(1) // subject is of type String (actually "a")
            .toBe("a")

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(1)  // subject is of type String (actually "a")
                .toBe("b")   // fails because "a" is not equal to "b"
        }

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(2) // subject is of type String, but assertion fails because key 2 does not exist
                .toBe("a")
        }
    }

    @Test
    fun getExisting() {
        expect(mapOf(1 to "a"))
            .getExisting(1, {   // subject inside this block is of type String (actually "a")
                toBe("a")
            })

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(1, {   // subject inside this block is of type String (actually "a")
                    toBe("b")   // fails because "a" is not equal to "b"
                })
        }

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(2, {   // subject is of type String, but assertion fails because key 2 does not exist
                    toBe("a")
                })
        }
    }

    @Test
    fun keysFeature() {
        // subject inside this block is of type Int
        expect(mapOf(1 to "a"))
            .keys   //subject is of type Set<Int> (actually <1>)
            .toContain {
                // subject inside this block is of type Int
                toBe(1)
            }

        fails {
            expect(mapOf(1 to "a"))
                .keys   //subject is of type Set<Int> (actually <1>)
                .contains { // subject inside this block is of type Int
                    toBe(2) // fails because 1 is not equal to 2
                }
        }
    }

    @Test
    fun keys() {
        expect(mapOf(1 to "a"))
            .keys { // subject inside this block is of type Set<Int> (actually <1>)
                toBe(setOf(1))
            }

        fails {
            expect(mapOf(1 to "a"))
                .keys { // subject inside this block is of type Set<Int> (actually <1>)
                    toBe(setOf(2))  // fails because <1> is not equal to <2>
                }
        }
    }

    @Test
    fun valuesFeature() {
        // subject inside this block is of type String
        expect(mapOf(1 to "a"))
            .values //subject is of type Collection<String> (actually <"a">)
            .toContain {
                // subject inside this block is of type String
                toBe("a")
            }

        fails {
            // subject inside this block is of type String
            expect(   // fails because "a" is not equal to "b"
                mapOf(1 to "a")
            )
                .values //subject is of type Collection<String> (actually <"a">)
                .toContain {
                    // subject inside this block is of type String
                    toBe("b")   // fails because "a" is not equal to "b"
                }
        }
    }

    @Test
    fun values() {
        expect(mapOf(1 to "a"))
            .values {   // subject inside this block is of type Collection<String> (actually <"a">)
                toBe(setOf("a"))
            }

        fails {
            expect(mapOf(1 to "a"))
                .values {   // subject inside this block is of type Collection<String> (actually <"a">)
                    toBe(setOf("b"))    // fails because <"a"> is not equal to <"b">
                }
        }
    }

    @Test
    fun asEntriesFeature() {
        // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
        expect(mapOf(1 to "a")).asEntries()
            .toContain {
                // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
                toBe(mapOf(1 to "a").entries.first())
            }

        fails {
            // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
            expect(
                mapOf(
                    1 to "a"
                )   // fails because <1,"a"> is not equal to <1,"b">
            ).asEntries()
                .toContain {
                    // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
                    toBe(mapOf(1 to "b").entries.first())   // fails because <1,"a"> is not equal to <1,"b">
                }
        }
    }

    @Test
    fun asEntries() {
        expect(mapOf(1 to "a")).asEntries { // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
            toBe(mapOf(1 to "a").entries)
        }

        fails {
            expect(mapOf(1 to "a")).asEntries { // subject inside this block is of type Map.Entry<Int, String> (actually <1,"a">)
                toBe(mapOf(1 to "b").entries)   // fails because <1,"a"> is not equal to <1,"b">
            }
        }
    }

    @Test
    fun isEmpty() {
        expect(emptyMap<Int, String>()).isEmpty()

        fails {
            expect(mapOf(1 to "a")).isEmpty()   //fails because the map is not empty
        }
    }

    @Test
    fun isNotEmpty() {
        expect(mapOf(1 to "a")).isNotEmpty()

        fails {
            expect(emptyMap<Int, String>()).isNotEmpty()   //fails because the map is empty
        }
    }
}
