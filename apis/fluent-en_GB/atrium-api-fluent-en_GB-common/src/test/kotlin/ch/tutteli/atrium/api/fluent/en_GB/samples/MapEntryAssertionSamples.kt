package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapEntryAssertionSamples {

    @Test
    fun isKeyValue() {
        expect(mapOf(1 to "a").entries.first()).isKeyValue(1, "a")

        fails {
            expect(mapOf(1 to "a").entries.first()).isKeyValue(1, "b")
        }
    }

    @Test
    fun keyFeature() {
        expect(mapOf(1 to "a").entries.first()).key.toBe(1)

        fails {
            expect(mapOf(1 to "a").entries.first()).key.toBe(2)
        }
    }

    @Test
    fun key() {
        expect(mapOf(1 to "a").entries.first())
            .key {
                isEqualComparingTo(1)
            }

        fails {
            expect(mapOf(1 to "a").entries.first())
                .key {
                    isEqualComparingTo(2)
                }
        }
    }

    @Test
    fun valueFeature() {
        expect(mapOf(1 to "a").entries.first()).value.toBe("a")

        fails {
            expect(mapOf(1 to "a").entries.first()).value.toBe("b")
        }
    }

    @Test
    fun value() {
        expect(mapOf(1 to "a").entries.first())
            .value {
                isEqualComparingTo("a")
            }

        fails {
            expect(mapOf(1 to "a").entries.first())
                .value {
                    isEqualComparingTo("b")
                }
        }
    }
}
