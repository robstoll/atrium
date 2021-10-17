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
            .toBeASuccess()  // Returns the value within Result
            .toEqual(1)

        fails {
            expect(success)
                .toBeASuccess() // Returns the value within Result
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

        expect(success)
            .toBeASuccess {
                toEqual(10)
                toBeLessThan(15)
            }

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
            .toBeAFailure<IllegalArgumentException>()
            .message
            .toEqual(message) // subject is now of type String

        fails {
            expect(failure)
                .toBeAFailure<IllegalArgumentException>()
                .message.toEqual("wrong parameter")
        }

        fails {
            expect(Result.success(1))
                .toBeAFailure<ArithmeticException>()
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
                .toBeAFailure<ArithmeticException> {
                    toBeAnInstanceOf<IllegalArgumentException>()
                    message.toEqual("can divide by one")
                }
        }
    }


}
