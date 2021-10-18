package ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.toBeAFailure
import ch.tutteli.atrium.api.fluent.en_GB.kotlin_1_3.toBeASuccess
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ResultExpectationSamples {

    @Test
    fun toBeASuccessFeature() {
        val success = Result.success(10)

        expect(success)
            .toBeASuccess()  // subject is now of type Int (actually 10)
            .toEqual(1)

        fails {
            expect(success)
                .toBeASuccess() // subject is now of type Int (actually 10)
                .toBeLessThan(5)
                .toBeGreaterThan(12)
        }

        fails {
            expect(Result.failure<ArithmeticException>(ArithmeticException()))
                .toBeASuccess()
        }

    }

    @Test
    fun toBeASuccess() {
        val success = Result.success(10)

        expect(success).toBeASuccess { // subject within this block is of type Int (actually 10)
                toEqual(10)
                toBeLessThan(15)
            } // subject here is back to type Result<Int>

        fails {
            expect(success)
                .toBeASuccess {
                    toBeGreaterThan(15)
                    toBeLessThan(5)
                }
        }
    }

    @Test
    fun toBeAFailureFeature() {
        val message = "wrong argument"
        val failure = Result.failure<IllegalArgumentException>(IllegalArgumentException(message))

        expect(failure)
            .toBeAFailure<IllegalArgumentException>() // subject is now of type IllegalArgumentException
            .message  // subject is now of type String
            .toEqual(message)

        fails {
            expect(failure)
                .toBeAFailure<IllegalArgumentException>()
                .message.toEqual("wrong parameter")
        }

        fails {
            expect(Result.success(1))
                .toBeAFailure<ArithmeticException>()  // fails
                .messageToContain("parameter") // not reported because toBeAFailure already fails
            //  use `toBeAFailure<...> { ... }` if you want that all expectations are evaluated
        }

    }

    @Test
    fun toBeAFailure() {
        val errorMessage = "can not divide by zero"
        val failure = Result.failure<ArithmeticException>(ArithmeticException(errorMessage))

        expect(failure)
            .toBeAFailure<ArithmeticException> {
                toBeAnInstanceOf<ArithmeticException>()
                message.toEqual(errorMessage)
            }

        fails {
            expect(failure)
                .toBeAFailure<ArithmeticException> { // fails
                    toBeAnInstanceOf<IllegalArgumentException>()  // fails
                    message.toEqual("can divide by one") // fails
                    // use `toBeAFailure<...> ()` if you want to stop on first failure
                }
        }

    }


}
