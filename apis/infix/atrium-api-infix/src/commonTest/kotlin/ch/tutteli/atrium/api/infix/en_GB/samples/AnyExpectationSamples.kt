package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class AnyExpectationSamples {

    @Test
    fun toEqual() {
        expect(12) toEqual 12 // holds

        fails {
            expect(12) toEqual 11
        }

        // holds, toEqual is based on equality, use toBeTheInstance for identity
        expect(listOf(1)) toEqual listOf(1)

        fails { // because array has not implemented equals, so is equivalent to toBeTheInstance
            expect(arrayOf(1)) toEqual arrayOf(1)
        }
    }

    @Test
    fun notToEqual() {
        expect(2) notToEqual 3

        fails {
            expect(12) notToEqual 12
        }

        expect(listOf(2)) notToEqual listOf(3)

        fails {
            expect(listOf(2)) notToEqual listOf(2)
        }
    }

    @Test
    fun toBeTheInstance() {
        val list = listOf(3)
        expect(list) toBeTheInstance list

        fails { // because toBeTheInstance is based on identity, use toEqual for equality
            expect(listOf(3)) toBeTheInstance listOf(3)
        }
    }

    @Test
    fun notToBeTheInstance() {
        // holds because notToBeTheInstance is based on identity, use notToEqual for equality
        expect(listOf(2)) notToBeTheInstance listOf(2)

        fails { // because notToBeTheInstance is based on identity, use notToEqual for equality
            val list = listOf(3)
            expect(list) notToBeTheInstance list
        }
    }

    @Test
    fun toEqualNullIfNullGivenElse() {
        expect<Int?>(null) toEqualNullIfNullGivenElse null

        expect<Int?>(1) toEqualNullIfNullGivenElse { // subject inside this expectation-group is of type Int (actually 1)
            it toBeLessThan 2
        } // subject here is back to type Int?

        fails { // because sub-expectation fails
            expect<Int?>(1) toEqualNullIfNullGivenElse {
                it toBeLessThan 0
            }
        }
    }

    @Test
    fun notToEqualNullFeature() {
        expect<Int?>(1) notToEqualNull o toBeLessThan 2
        //                             | subject is now of type Int

        fails {
            expect<Int?>(null) notToEqualNull o toBeLessThan 2
            //                      |             | not reported because `notToEqualNull` already fails
            //                      | fails
        }
    }

    @Test
    fun notToEqualNull() {
        expect<Int?>(1) notToEqualNull { // subject is now of type Int
            it toBeGreaterThan 0
            it toBeLessThan 10
        } /* subject remains type Int also after the block
        */ toEqual 1


        fails { // because subject is null, but since we use an expectation-group...
            expect<Int?>(null) notToEqualNull {
                it toBeGreaterThan 2  // ...reporting mentions that subject was expected `to be greater than: 2`
            }
        }

        fails { // because sub-expectation fails
            expect<Int?>(1) notToEqualNull {
                it toBeLessThan 0
            }
        }

        fails {
            // because you forgot to define an expectation in the expectation-group block
            // use `notToEqualNull o` if this is all you expect
            expect<Int?>(1) notToEqualNull { }
        }
    }

    @Test
    fun toBeAnInstanceOfFeature() {
        val n: Number = 1
        expect(n).toBeAnInstanceOf<Int>() toBeGreaterThan 0
        //                   | subject is now of type Int

        fails {
            expect("A").toBeAnInstanceOf<Long>() toBeLessThan 2L
            //                                      | not shown in reporting as `toBeAnInstanceOf` already fails
        }
    }

    @Test
    fun toBeAnInstanceOf() {
        val n: Number = 16
        expect(n).toBeAnInstanceOf<Int> { // subject is now of type Int
            it toBeGreaterThanOrEqualTo 15
        } /* subject remains type Int also after the block
        */ toBeLessThan 20

        fails { // because wrong type expected (Long instead of String), but since we use an expectation-group...
            expect("A").toBeAnInstanceOf<Long> {
                it toEqual 43 // ...reporting mentions that subject was expected `to equal: 43`
            }
        }

        fails { // because sub-expectation fails
            expect(n).toBeAnInstanceOf<Long> {
                it toEqual -1L
            }
        }

        fails {
            // because you forgot to define an expectation in the expectation-group block
            // use `.toBeAnInstanceOf<Int>()` if this is all you expect
            expect<Number>(1).toBeAnInstanceOf<Int> { }
        }
    }

    @Test
    fun notToBeAnInstanceOf() {
        val n: Number = 16L
        expect(n).notToBeAnInstanceOf<Int>()
        fails {
            // fails because n is actually instance of Long
            expect(n).notToBeAnInstanceOf<Long>()
        }
    }

    @Test
    fun notToBeAnInstanceOfKClass() {
        val n: Number = 16L
        expect(n) notToBeAnInstanceOf Int::class
        fails {
            // fails because n is actually instance of Long
            expect(n) notToBeAnInstanceOf Long::class
        }
    }

    @Test
    fun notToBeAnInstanceOfKClasses() {
        val n: Number = 16L
        expect(n) notToBeAnInstanceOf types(Int::class, Float::class, Double::class)
        fails {
            // fails because n is actually instance of Long
            expect(n) notToBeAnInstanceOf types(Int::class, Long::class)
        }
    }

    @Test
    fun andFeature() {
        // `and` is just a filler word does not have any behaviour
        expect(13) toBeGreaterThan 5 and o toBeLessThan 20

        // i.e. the above is equivalent to:
        expect(13) toBeGreaterThan 5 toBeLessThan 20
    }

    @Test
    fun and() {
        expect(13).toBeAnInstanceOf<Int>() and {
            it toBeGreaterThan 5
            it toBeLessThan 20
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(13).toBeAnInstanceOf<Int>() and {
                it notToEqualOneOf values(1, 2, 13) // fails
                it toBeLessThan 10                  // still evaluated and included in the error report
                //                                     use ` and o` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun notToEqualOneOf() {
        expect(99) notToEqualOneOf values(1, 2, 3, 4)

        fails {
            expect(1) notToEqualOneOf values(1, 2, 3, 4)
        }
    }

    @Test
    fun notToEqualOneIn() {
        expect(99) notToEqualOneIn listOf(1, 2, 3, 4)

        fails {
            expect(1) notToEqualOneIn listOf(1, 2, 3, 4)
        }
    }
}
