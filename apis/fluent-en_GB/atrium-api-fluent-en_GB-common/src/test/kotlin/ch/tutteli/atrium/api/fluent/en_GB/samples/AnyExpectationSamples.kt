package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class AnyExpectationSamples {

    @Test
    fun toEqual() {
        expect(12).toEqual(12) // holds

        fails {
            expect(12).toEqual(11)
        }

        // holds, toEqual is based on equality, use toBeTheInstance for identity
        expect(listOf(1)).toEqual(listOf(1))

        fails { // because array has not implemented equals, so is the same as using toBeTheInstance
            expect(arrayOf(1)).toEqual(arrayOf(1))
        }
    }

    @Test
    fun notToEqual() {
        expect(2).notToEqual(3)

        fails {
            expect(12).notToEqual(12)
        }

        expect(listOf(2)).notToEqual(listOf(3))

        fails {
            expect(listOf(2)).notToEqual(listOf(2))
        }
    }

    @Test
    fun toBeTheInstance() {
        val list = listOf(3)
        expect(list).toBeTheInstance(list)

        fails { // because toBeTheInstance is based on identity, use toEqual for equality
            expect(listOf(3)).toBeTheInstance(listOf(3))
        }
    }

    @Test
    fun notToBeTheInstance() {
        // holds because notToBeTheInstance is based on identity, use noToEqual for equality
        expect(listOf(2)).notToBeTheInstance(listOf(2))

        fails { // because notToBeTheInstance is based on identity, use notToEqual for equality
            val list = listOf(3)
            expect(list).notToBeTheInstance(list)
        }
    }

    @Test
    fun toEqualNullIfNullGivenElse() {
        expect<Int?>(null).toEqualNullIfNullGivenElse(null)

        expect<Int?>(1).toEqualNullIfNullGivenElse { // subject inside this block is of type Int
            toBeLessThan(2)
        }  // subject here is back to type Int?

        fails { // because sub-expectation fails
            expect<Int?>(1).toEqualNullIfNullGivenElse {
                toBeLessThan(0)
            }
        }
    }

    @Test
    fun notToEqualNullFeature() {
        expect<Int?>(1)
            .notToEqualNull() // subject is now of type Int
            .toBeLessThan(2)

        fails {
            expect<Int?>(null)
                .notToEqualNull() // fails
                .toBeLessThan(2) // not reported because `notToEqualNull` already fails
        }
    }

    @Test
    fun notToEqualNull() {
        expect<Int?>(1)
            .notToEqualNull { // subject is now of type Int
                toBeGreaterThan(0)
                toBeLessThan(10)
            } // subject remains type Int also after the block
            .toEqual(1)

        fails {
            // because you forgot to define an expectation in the expectation group block
            // use `notToEqualNull()` if this is all you expect
            expect<Int?>(1).notToEqualNull { }
        }


        fails { // because subject is null, but since we use a block...
            expect<Int?>(null).notToEqualNull {
                toBeGreaterThan(2) // ...reporting mentions that subject was expected `to be greater than: 2`
            }
        }

        fails { // because sub-expectation fails
            expect<Int?>(1).notToEqualNull {
                toBeLessThan(0)
            }
        }
    }

    @Test
    fun toBeAnInstanceOfFeature() {
        val n: Number = 1
        expect(n)
            .toBeAnInstanceOf<Int>() // subject is now of type Int
            .toBeGreaterThan(0)

        fails {
            expect("A")
                .toBeAnInstanceOf<Long>()
                .toBeLessThan(2L) // not shown in reporting as `toBeA<Long>()` already fails

        }
    }

    @Test
    fun toBeAnInstanceOf() {
        val n: Number = 16
        expect(n)
            .toBeAnInstanceOf<Int> { // subject is now of type Int
                toBeGreaterThanOrEqual(15)
            } // subject remains type Int also after the block
            .toBeLessThan(20)

        fails { // because wrong type expected (Long instead of String), but since we use a block...
            expect("A").toBeAnInstanceOf<Long> {
                toEqual(43) // ...reporting mentions that subject was expected `to equal: 43`
            }
        }

        fails { // because sub-expectation fails
            expect(n).toBeAnInstanceOf<Long> {
                toEqual(-1L)
            }
        }
    }

    @Test
    fun andFeature() {
        // `and` is just a filler word; does not have any behaviour
        expect(13).toBeGreaterThan(5).and.toBeLessThan(20)

        // i.e. the above is equivalent to:
        expect(13).toBeGreaterThan(5).toBeLessThan(20)
    }

    @Test
    fun and() {
        expect(13).toBeAnInstanceOf<Int>().and {
            toBeGreaterThan(5)
            toBeLessThan(20)
        }

        fails {
            expect(13).toBeAnInstanceOf<Int>().and {
                // introduces an expectation group block
                // all expectations are evaluated inside an expectation group block; for more details:
                // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups
                // use `.and.` if you want fail fast behaviour

                notToEqualOneOf(1, 2, 13) // fails
                toBeLessThan(10)          // still evaluated and included in the error report
            }
        }
    }

    @Test
    fun notToEqualOneOf() {
        expect(99).notToEqualOneOf(1, 2, 3, 4)

        fails {
            expect(1).notToEqualOneOf(1, 2, 3, 4)
        }
    }

    @Test
    fun notToEqualOneIn() {
        expect(99).notToEqualOneIn(listOf(1, 2, 3, 4))

        fails {
            expect(1).notToEqualOneIn(listOf(1, 2, 3, 4))
        }
    }

    data class Person(val age: Int)

    private val customers = listOf(Person(21))

    @Test
    fun because() {
        expect("filename")
            .because("? is not allowed in file names on Windows") {
                notToContain("?")
            }

        expect(customers).all {
            because("the legal age of maturity in Switzerland is 18") {
                feature { f(it::age) }.toBeGreaterThanOrEqual(18)
            }
        }
    }
}
