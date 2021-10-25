package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.toBeAFailure
import ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.toBeASuccess
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ResultExpectationSamples {

    @Test
    fun toBeASuccessFeature() {

        expect(Result.success(10))
            .toBeASuccess() // subject is now of type Int (actually 10)
            .toEqual(1)

        fails { // because sub-expectation fails
            expect(Result.success(10))
                .toBeASuccess()      // subject is now of type Int (actually 10)
                .toBeLessThan(5)     // fails
                .toBeGreaterThan(12) // not reported because toBeLessThan already fails
            //                          use `.toBeASuccess { ... }` if you want that all expectations are evaluated
        }

        fails { // because it was a Failure
            expect(Result.failure<Int>(ArithmeticException()))
                .toBeASuccess()      // fails
                .toBeGreaterThan(12) // not reported because toBeLessThan already fails
            //                          use `.toBeASuccess { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun toBeASuccess() {

        expect(Result.success(10)).toBeASuccess { // subject within this block is of type Int (actually 10)
            toEqual(10)
            toBeLessThan(15)
        } // subject here is back to type Result<Int>

        fails { // because sub-expectation fails
            expect(Result.success(10)).toBeASuccess {
                toBeGreaterThan(15) // fails
                toBeLessThan(5)     // still evaluated even though toBeGreaterThan already fails
                //                     use `.toBeASuccess.` if you want a fail fast behaviour
            }
        }

        fails { // because it was a Failure, but since we use a block...
            expect(Result.failure<Int>(ArithmeticException())).toBeASuccess {
                toBeGreaterThan(12) // ...reporting mentions that subject was expected `to be greater than: 12`
                //                     use `.toBeASuccess.` if you want a fail fast behaviour
            }

        }
    }

    @Test
    fun toBeAFailureFeature() {
        val message = "wrong argument"
        val failure = Result.failure<Int>(IllegalArgumentException(message))

        expect(failure)
            .toBeAFailure<IllegalArgumentException>() // subject is now of type IllegalArgumentException
            .messageToContain("argument")


        fails { // because sub-expectation fails
            expect(failure)
                .toBeAFailure<IllegalArgumentException>()
                .messageToContain("parameter") // fails
        }

        fails { // because wrong Expectation type expected
            expect(failure)
                .toBeAFailure<ArithmeticException>() // fails
                .messageToContain("parameter")       // not reported because toBeAFailure already fails
            //                                          use `toBeAFailure<...> { ... }` if you want that all expectations are evaluated
        }

        fails { // because it was a Success
            expect(Result.success(10))
                .toBeAFailure<IllegalArgumentException>() // fails
                .messageToContain("parameter")            // not reported because toBeAFailure already fails
            //                                               use `toBeAFailure<...> { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun toBeAFailure() {
        val errorMessage = "can not divide by zero"
        val failure = Result.failure<Int>(ArithmeticException(errorMessage))

        expect(failure).toBeAFailure<ArithmeticException> {  // subject within this block is of type ArithmeticException
            messageToContain("parameter")
        } // subject here is back to type Result<Int>

        fails { // because sub-expectation fails
            expect(failure).toBeAFailure<IllegalArgumentException> {
                messageToContain("parameter") // fails
                message.toStartWith("wrong")  // still evaluated even though messageToContain already fails
                //                               use `.toBeAFailure.` if you want a fail fast behaviour
            }
        }

        fails { // because wrong Expectation type expected, but since we use a block...
            expect(failure).toBeAFailure<ArithmeticException> {
                messageToContain("parameter") // ...reporting mentions that subject's message was expected `to contain: "parameter"``
                //                               use `.toBeAFailure.` if you want a fail fast behaviour
            }
        }

        fails { // because it was a Success, but since we use a block
            expect(Result.success(10)).toBeAFailure<IllegalArgumentException> {
                messageToContain("parameter") // ...reporting mentions that subject's message was expected `to contain: "parameter"``
                //                               use `.toBeAFailure.` if you want a fail fast behaviour
            }
        }
    }


}
