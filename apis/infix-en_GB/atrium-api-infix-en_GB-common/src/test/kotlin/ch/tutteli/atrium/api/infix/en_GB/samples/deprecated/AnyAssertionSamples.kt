//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB.samples.deprecated

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class AnyAssertionSamples {

    @Test
    fun toBe() {
        expect(12) toBe 12 // holds

        fails {
            expect(12) toBe 11
        }

        // holds, toBe is based on equality, use isSameAs for identity
        expect(listOf(1)) toBe listOf(1)

        fails { // because array has not implemented equals, so is equivalent to isSameAs
            expect(arrayOf(1)) toBe arrayOf(1)
        }
    }


    @Test
    fun because() {
        expect(12) because of("this is true as anything can ever be") {
            toBe(12)
        }

        fails {
            expect(12) because of("won't hold, it's not equal") {
                toBe(11)
            }
        }

        expect(listOf(1)) because of("toBe is based on equality, use isSameAs for identity") {
            toBe(listOf(1))
        }

        fails {
            expect(arrayOf(1)) because of("array has not implemented equals, so is equivalent to isSameAs") {
                toBe(arrayOf(1))
            }
        }
    }

    @Test
    fun notToBe() {
        expect(2) notToBe 3

        fails {
            expect(12) notToBe 12
        }

        expect(listOf(2)) notToBe listOf(3)

        fails {
            expect(listOf(2)) notToBe listOf(2)
        }
    }

    @Test
    fun isSameAs() {
        val list = listOf(3)
        expect(list) isSameAs list

        fails {
            expect(listOf(3)) isSameAs listOf(3) // fails because isSameAs is based on identity, use toBe for equality
        }
    }

    @Test
    fun isNotSameAs() {
        expect(listOf(2)) isNotSameAs listOf(2) // holds because isSameAs is based on identity, use notToBe for equality

        fails {
            val list = listOf(3)
            expect(list) isNotSameAs list // fails because isNotSameAs is based on identity, use toBe for equality
        }
    }

    @Test
    fun toBeNullIfNullGivenElse() {
        expect<Int?>(null) toBeNullIfNullGivenElse null

        expect<Int?>(1) toBeNullIfNullGivenElse { // subject inside this block is of type Int (actually 1)
            it isLessThan 2
        } // subject here is back to type Int?

        fails {
            expect<Int?>(1) toBeNullIfNullGivenElse { // subject inside this block is of type Int (actually 1)
                it isLessThan 0
            } // subject here is back to type Int?
        }
    }

    @Test
    fun notToBeNullFeature() {
        expect<Int?>(1) notToBeNull o isLessThan 2
        //                           | subject is now of type Int (actually 1)

        fails {
            expect<Int?>(null) notToBeNull o isLessThan 2
            //                              |             | not reported because `notToBeNull` already fails
            //                              | fails
        }
    }

    @Test
    fun notToBeNull() {
        expect<Int?>(1) notToBeNull { // subject is now of type Int
            it isGreaterThan 0
            it isLessThan 10
        } toBe 1 // subject here remains type Int

        fails {
            // because you forgot to define an assertion in the assertion group block
            // use `notToBeNull()` if this is all you want to assert
            expect<Int?>(1) notToBeNull { }
        }

        fails {
            // notToBeNull already fails, reporting mentions that subject was expected `to be: 2`
            expect<Int?>(null) notToBeNull { // subject inside this block is of type Int (actually 1)
                it toBe 2
            }  // subject here remains type Int
        }

        fails {
            expect<Int?>(1) notToBeNull { // subject inside this block is of type Int (actually 1)
                it isLessThan 0
            } // subject here remains type Int
        }
    }

    @Test
    fun isAFeature() {
        val n: Number = 1
        expect(n).isA<Int>() isGreaterThan 0
        //          | subject is now of type Int

        fails {
            expect("A").isA<Long>() isLessThan 2L // not shown in reporting as `isA<Long>()` already fails
        }
    }

    @Test
    fun isA() {
        val n: Number = 16
        expect(n).isA<Int> { // subject is now of type Int, within this block but also afterwards
            it isGreaterThanOrEqual 15
        } isLessThan 20

        fails {
            // because wrong type expected (Long instead of String)
            expect("A").isA<Long> {
                it toBe 43
            }
        }

        fails {
            // type fits, but sub-assertion fails
            expect(54L).isA<Long> {
                it toBe -1L
            }
        }
    }

    @Test
    fun andFeature() {
        // and is just a filler word does not have any behaviour
        expect(13) isGreaterThan 5 and o isLessThan 20

        // i.e. the above is equivalent to:
        expect(13) isGreaterThan 5 isLessThan 20
    }

    @Test
    fun and() {
        expect(13).isA<Int>() and {
            it isGreaterThan 5
            it isLessThan 20
        }

        fails {
            expect(13).isA<Int>() and {
                // all assertions are evaluated inside an assertion group block; for more details:
                // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
                it isNoneOf values(1, 2, 13)  // fails
                it isLessThan 10  //                                 still evaluated and included in the error report
                //                                                   use `.and.` if you want fail fast behaviour
            }
        }
    }

    @Test
    fun isNoneOf() {
        expect(99) isNoneOf values(1, 2, 3, 4)

        fails {
            expect(1) isNoneOf values(1, 2, 3, 4)
        }
    }

    @Test
    fun isNotIn() {
        expect(99) isNotIn listOf(1, 2, 3, 4)

        fails {
            expect(1) isNotIn listOf(1, 2, 3, 4)
        }
    }

    data class Person(val age: Int)

    private val customers = listOf(Person(21))

    @Test
    fun becauseOf() {
        expect("filename") because of("? is not allowed in file names on Windows") {
            it containsNot "?"
        }

        expect(customers) all {
            it because of("the legal age of maturity in Switzerland is 18") {
                feature { f(it::age) } isGreaterThanOrEqual 18
            }
        }
    }
}
