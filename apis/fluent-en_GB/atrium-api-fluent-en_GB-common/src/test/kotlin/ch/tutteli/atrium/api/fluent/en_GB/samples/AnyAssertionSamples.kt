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
       // holds because isSameAs is based on identity, use notToBe for equality
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
        expect<Int?>(1)
          .notToBeNull() // subject is now of type Int
          .isLessThan(2)

        fails {
            expect<Int?>(null)
              .notToBeNull()
              .isLessThan(2) // not shown in reporting as notToBeNull already fails
        }
    }

    @Test
    fun notToBeNull() {
        expect<Int?>(1).notToBeNull { // subject is now of type Int, within this block but also afterwards
          isGreaterThan(0)
          isLessThan(10)
        }.toBe(1)

        fails {
            // because you forgot to define an assertion in the assertion group block
            // use `notToBeNull()` if this is all you want to assert
            expect<Int?>(1).notToBeNull { }
        }


        fails {
            // notToBeNull already fails, reporting mentions that subject was expected `to be: 2`
            expect<Int?>(null).notToBeNull {
                toBe(2)
            }
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
        val n: Number = 1
        expect(n)
            .isA<Int>() // subject is now of type Int
            .isGreaterThan(0)

        fails {
            expect("A")
                .isA<Long>()
                .isLessThan(2L) // not shown in reporting as isA already fails

        }
    }

    @Test
    fun isA() {
        val n: Number = 16
        expect(n).isA<Int> { // subject is now of type Int, within this block but also afterwards
            isGreaterThanOrEqual(15)
        }.isLessThan(20)

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
