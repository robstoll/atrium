package ch.tutteli.atrium.api.fluent.en_GB.samples.deprecated

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapAssertionSamples {

    @Test
    fun containsFeature() {
        expect(mapOf(1 to "a")).contains.inAnyOrder.entries(1 to "a")

        fails {
            expect(mapOf(1 to "a")).contains.inAnyOrder.entries(1 to "b")
        }
    }

    @Test
    fun containsPair() {
        expect(mapOf(1 to "a")).contains(1 to "a")

        fails {
            expect(mapOf(1 to "a")).contains(1 to "b")
        }
    }

    @Test
    fun containsOnlyPair() {
        expect(mapOf(1 to "a")).containsOnly(1 to "a")

        fails {
            expect(mapOf(1 to "a", 1 to "b")).containsOnly(1 to "a")
        }
    }

    @Test
    fun containsKeyValue() {
        expect(mapOf(1 to "a")).contains(KeyValue(1, { toBe("a") }))

        fails {
            expect(mapOf(1 to "a")).contains(KeyValue(1, { toBe("b") }))
        }
    }

    @Test
    fun containsOnlyKeyValue() {
        expect(mapOf(1 to "a")).containsOnly(KeyValue(1, { toBe("a") }))

        fails {
            expect(mapOf(1 to "a", 1 to "b")).containsOnly(KeyValue(1, { toBe("a") }))
        }
    }

    @Test
    fun containsEntriesOf() {
        expect(mapOf(1 to "a")).containsEntriesOf(listOf(1 to "a"))

        fails {
            expect(mapOf(1 to "a")).containsEntriesOf(listOf(1 to "b"))
        }
    }

    @Test
    fun containsOnlyEntriesOf() {
        expect(mapOf(1 to "a")).containsEntriesOf(listOf(1 to "a"))

        fails {
            expect(mapOf(1 to "a", 1 to "b")).containsEntriesOf(listOf(1 to "a"))
        }
    }

    @Test
    fun containsKey() {
        expect(mapOf(1 to "a")).containsKey(1)

        fails {
            expect(mapOf(1 to "a")).containsKey(2)
        }
    }

    @Test
    fun containsNotKey() {
        expect(mapOf(1 to "a")).containsNotKey(2)

        fails {
            expect(mapOf(1 to "a")).containsNotKey(1)
        }
    }

    @Test
    fun getExisting() {
        expect(mapOf(1 to "a")).getExisting(1)

        fails {
            expect(mapOf(1 to "a")).getExisting(2)
        }
    }

    @Test
    fun getExistingKeyAssertion() {
        expect(mapOf(1 to "a")).getExisting(1, {
            toBe("a")
        })

        fails {
            expect(mapOf(1 to "a")).getExisting(1, {
                toBe("b")
            })
        }
    }

    @Test
    fun keysFeature() {
        expect(mapOf(1 to "a"))
            .keys
            .contains {
                toBe(1)
            }

        fails {
            expect(mapOf(1 to "a"))
                .keys
                .contains {
                    toBe(2)
                }
        }
    }

    @Test
    fun keys() {
        expect(mapOf(1 to "a"))
            .keys {
                toBe(setOf(1))
            }

        fails {
            expect(mapOf(1 to "a"))
                .keys {
                    toBe(setOf(2))
                }
        }
    }

    @Test
    fun valuesFeature() {
        expect(mapOf(1 to "a"))
            .values
            .contains {
                toBe("a")
            }

        fails {
            expect(mapOf(1 to "a"))
                .values
                .contains {
                    toBe("b")
                }
        }
    }

    @Test
    fun values() {
        expect(mapOf(1 to "a"))
            .values {
                toBe(setOf("a"))
            }

        fails {
            expect(mapOf(1 to "a"))
                .values {
                    toBe(setOf("b"))
                }
        }
    }

    @Test
    fun asEntriesFeature() {
        expect(mapOf(1 to "a")).asEntries().contains {
            toBe(mapOf(1 to "a").entries.first())
        }

        fails {
            expect(mapOf(1 to "a")).asEntries().contains {
                toBe(mapOf(1 to "b").entries.first())
            }
        }
    }

    @Test
    fun asEntries() {
        expect(mapOf(1 to "a")).asEntries {
            toBe(mapOf(1 to "a").entries)
        }

        fails {
            expect(mapOf(1 to "a")).asEntries {
                toBe(mapOf(1 to "b").entries)
            }
        }
    }

    @Test
    fun isEmpty() {
        expect(mapOf<Int, String>()).isEmpty()

        fails {
            expect(mapOf(1 to "a")).isEmpty()
        }
    }

    @Test
    fun isNotEmpty() {
        expect(mapOf(1 to "a")).isNotEmpty()

        fails {
            expect(mapOf<Int, String>()).isNotEmpty()
        }
    }
}
