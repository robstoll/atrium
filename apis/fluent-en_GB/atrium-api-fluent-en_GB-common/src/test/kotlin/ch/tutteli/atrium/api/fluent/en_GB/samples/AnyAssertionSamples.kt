package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class AnyAssertionSamples {

    @Test
    fun toBe() {
        expect(12).toBe(12) // holds

        fails {
            expect(12).toBe(11)
        }

        // holds, toBe is based on equality, use isSameAs for identity
        expect(listOf(1)).toBe(listOf(1))

        fails { // because array has not implemented equals, so is equivalent to isSameAs
            expect(arrayOf(1)).toBe(arrayOf(1))
        }
    }

    @Test
    fun notToBe() {
        expect(2).notToBe(3)

        fails {
            expect(12).notToBe(12)
        }

        expect(listOf(2)).notToBe(listOf(3))

        fails {
            expect(listOf(2)).notToBe(listOf(2))
        }
    }

    @Test
    fun isSameAs() {
        val list = listOf(3)
        expect(list).isSameAs(list)

        fails {
            // fails because isSameAs is based on identity, use toBe for equality
            expect(listOf(3)).isSameAs(listOf(3))
        }
    }

    @Test
    fun isNotSameAs() {
        expect(listOf(2)).isNotSameAs(listOf(2))

        fails {
            val list = listOf(3)
            expect(list).isNotSameAs(list)
        }
    }

    @Test
    fun toBeNullIfNullGivenElse() {
        expect<Int?>(null).toBeNullIfNullGivenElse(null)

        expect<Int?>(1).toBeNullIfNullGivenElse {
            toBe(1)
        }

        fails {
            // sub-assertion fails
            expect<Int?>(1).toBeNullIfNullGivenElse {
                toBe(2)
            }
        }
    }

    @Test
    fun notToBeNullFeature() {
        expect<Int?>(1).notToBeNull()

        fails {
            expect<Int?>(null).notToBeNull()
        }
    }

    @Test
    fun notToBeNull() {
        expect<Int?>(1).notToBeNull {
            toBe(1)
        }

        fails {
            expect<Int?>(1).notToBeNull {}
        }

        fails {
            // sub-assertion fails
            expect<Int?>(1).notToBeNull {
                toBe(2)
            }
        }
    }

    @Test
    fun isAFeature() {
        expect(1).isA<Int>()

        fails {
            expect("A").isA<Long>()
        }
    }

    @Test
    fun isA() {
        expect(16).isA<Int> {
            toBe(16)
        }

        fails {
            // wrong type
            expect("A").isA<Long> {
                toBe(43)
            }
        }

        fails {
            // type fits, but sub-assertion fails
            expect(54L).isA<Long> {
                toBe(-1)
            }
        }
    }

    @Test
    fun andFeature() {
        expect(13)
            .isA<Int>()
            .and
            .toBe(13)

        fails {
            expect(1)
                .isA<Long>()
                .and
                .toBe(77)
        }
    }

    @Test
    fun and() {
        expect(13)
            .isA<Int>()
            .and {
                toBe(13)
            }

        fails {
            // sub-assertion fails
            expect(1L)
                .isA<Long>()
                .and {
                    toBe(67)
                }
        }
    }

    @Test
    fun isNoneOf() {
        expect(99).isNoneOf(1, 2, 3, 4)

        fails {
            expect(1).isNoneOf(1, 2, 3, 4)
        }
    }

    @Test
    fun isNotIn() {
        expect(99).isNotIn(listOf(1, 2, 3, 4))

        fails {
            expect(1).isNotIn(listOf(1, 2, 3, 4))
        }
    }
}
