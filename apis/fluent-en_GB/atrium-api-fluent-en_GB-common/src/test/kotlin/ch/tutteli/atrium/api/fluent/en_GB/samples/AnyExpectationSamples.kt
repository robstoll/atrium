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

        fails {
            // fails because toBeTheInstance is based on identity, use toEqual for equality
            expect(listOf(3)).toBeTheInstance(listOf(3))
        }
    }

    @Test
    fun notToBeTheInstance() {
        // holds because notToBeTheInstance is based on identity, use noToEqual for equality
        expect(listOf(2)).notToBeTheInstance(listOf(2))

        fails {
            val list = listOf(3)
            expect(list).notToBeTheInstance(list)
        }
    }

    @Test
    fun toEqualNullIfNullGivenElse() {
        expect<Int?>(null).toEqualNullIfNullGivenElse(null)

        expect<Int?>(1).toEqualNullIfNullGivenElse {
            isLessThan(2)
        }

        fails {
            // sub-expectation fails
            expect<Int?>(1).toEqualNullIfNullGivenElse {
                isLessThan(0)
            }
        }
    }

    @Test
    fun notToEqualNullFeature() {
        expect<Int?>(1)
            .notToEqualNull() // subject is now of type Int
            .isLessThan(2)

        fails {
            expect<Int?>(null)
                .notToEqualNull() // fails
                .isLessThan(2) // not shown in reporting as notToEqualNull already fails
        }
    }

    @Test
    fun notToEqualNull() {
        expect<Int?>(1).notToEqualNull { // subject is now of type Int, within this block but also afterwards
            isGreaterThan(0)
            isLessThan(10)
        }.toEqual(1)

        fails {
            // because you forgot to define an expectation in the expectation group block
            // use `notToEqualNull()` if this is all you want to assert
            expect<Int?>(1).notToEqualNull { }
        }


        fails {
            // notToBeNull already fails, reporting mentions that subject was expected `to equal: 2`
            expect<Int?>(null).notToEqualNull {
                isGreaterThan(2)
            }
        }

        fails {
            // sub-expectation fails
            expect<Int?>(1).notToEqualNull {
                isLessThan(0)
            }
        }
    }

    @Test
    fun toBeAFeature() {
        val n: Number = 1
        expect(n)
            .toBeA<Int>() // subject is now of type Int
            .isGreaterThan(0)

        fails {
            expect("A")
                .toBeA<Long>()
                .isLessThan(2L) // not shown in reporting as `toBeA<Long>()` already fails

        }
    }

    @Test
    fun toBeA() {
        val n: Number = 16
        expect(n).toBeA<Int> { // subject is now of type Int, within this block but also afterwards
            isGreaterThanOrEqual(15)
        }.isLessThan(20)

        fails {
            // because wrong type expected (Long instead of String)
            expect("A").toBeA<Long> {
                toEqual(43)
            }
        }

        fails {
            // type fits, but sub-expectation fails
            expect(54L).toBeA<Long> {
                toEqual(-1L)
            }
        }
    }

    @Test
    fun andFeature() {
        // and is just a filler word; does not have any behaviour
        expect(13).isGreaterThan(5).and.isLessThan(20)

        // i.e. the above is equivalent to:
        expect(13).isGreaterThan(5).isLessThan(20)
    }

    @Test
    fun and() {
        expect(13).toBeA<Int>().and {
            isGreaterThan(5)
            isLessThan(20)
        }

        fails {
            expect(13).toBeA<Int>().and {
                // introduces an expectation group block
                // all expectations are evaluated inside an expectation group block; for more details:
                // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups
                // use `.and.` if you want fail fast behaviour

                notToEqualOneOf(1, 2, 13) // fails
                isLessThan(10)            // still evaluated and included in the error report
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
                containsNot("?")
            }

        expect(customers).all {
            because("the legal age of maturity in Switzerland is 18") {
                feature { f(it::age) }.isGreaterThanOrEqual(18)
            }
        }
    }
}
