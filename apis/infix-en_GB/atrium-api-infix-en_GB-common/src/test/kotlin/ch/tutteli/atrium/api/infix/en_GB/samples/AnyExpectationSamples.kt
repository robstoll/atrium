
package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
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

        expect<Int?>(1) toEqualNullIfNullGivenElse { // subject inside this block is of type Int (actually 1)
            it isLessThan 2
        } // subject here is back to type Int?

        fails { // because sub-expectation fails
            expect<Int?>(1) toEqualNullIfNullGivenElse {
                it isLessThan 0
            }
        }
    }

    @Test
    fun notToEqualNullFeature() {
        expect<Int?>(1) notToEqualNull o isLessThan 2
        //                             | subject is now of type Int

        fails {
            expect<Int?>(null) notToEqualNull o isLessThan 2
            //                      |             | not reported because `notToEqualNull` already fails
            //                      | fails
        }
    }

    @Test
    fun notToEqualNull() {
        expect<Int?>(1) notToEqualNull { // subject is now of type Int
            it isGreaterThan 0
            it isLessThan 10
        } /* subject remains type Int also after the block
        */ toEqual 1

        fails {
            // because you forgot to define an expectation in the expectation group block
            // use `notToEqualNull o` if this is all you expect
            expect<Int?>(1) notToEqualNull { }
        }

        fails { // because subject is null, but since we use a block...
            expect<Int?>(null) notToEqualNull {
                it isGreaterThan 2  // ...reporting mentions that subject was expected `to be greater than: 2`
            }
        }

        fails { // because sub-expectation fails
            expect<Int?>(1) notToEqualNull {
                it isLessThan 0
            }
        }
    }

    @Test
    fun toBeAnInstanceOfFeature() {
        val n: Number = 1
        expect(n).toBeAnInstanceOf<Int>() isGreaterThan 0
        //                   | subject is now of type Int

        fails {
            expect("A").toBeAnInstanceOf<Long>() isLessThan 2L
            //                        | not shown in reporting as `toBeA<Long>()` already fails
        }
    }

    @Test
    fun toBeAnInstanceOf() {
        val n: Number = 16
        expect(n).toBeAnInstanceOf<Int> { // subject is now of type Int
            it isGreaterThanOrEqual 15
        } /* subject remains type Int also after the block
        */ isLessThan 20

        fails { // because wrong type expected (Long instead of String), but since we use a block...
            expect("A").toBeAnInstanceOf<Long> {
                it toEqual 43 // ...reporting mentions that subject was expected `to equal: 43`
            }
        }

        fails { // because sub-expectation fails
            expect(n).toBeAnInstanceOf<Long> {
                it toEqual -1L
            }
        }
    }

    @Test
    fun andFeature() {
        // `and` is just a filler word does not have any behaviour
        expect(13) isGreaterThan 5 and o isLessThan 20

        // i.e. the above is equivalent to:
        expect(13) isGreaterThan 5 isLessThan 20
    }

    @Test
    fun and() {
        expect(13).toBeAnInstanceOf<Int>() and {
            it isGreaterThan 5
            it isLessThan 20
        }

        fails {
            expect(13).toBeAnInstanceOf<Int>() and {
                // introduces an expectation group block
                // all expectations are evaluated inside an expectations group block; for more details:
                // https://github.com/robstoll/atrium#define-single-expectations-or-expectation-groups
                it notToEqualOneOf values(1, 2, 13)  // fails
                it isLessThan 10              // still evaluated and included in the error report
                // use `.and.` if you want fail fast behaviour
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

    data class Person(val age: Int)

    private val customers = listOf(Person(21))

    @Test
    fun becauseOf() {
        expect("filename") because of("? is not allowed in file names on Windows") {
            it notToContain "?"
        }

        expect(customers) all {
            it because of("the legal age of maturity in Switzerland is 18") {
                feature { f(it::age) } isGreaterThanOrEqual 18
            }
        }
    }
}
