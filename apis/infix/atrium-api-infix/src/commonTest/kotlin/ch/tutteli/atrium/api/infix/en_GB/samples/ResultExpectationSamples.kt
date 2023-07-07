package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class ResultExpectationSamples {

    @Test
    fun toBeASuccessFeature() {

        expect(Result.success(10)) toBe aSuccess toEqual 10 toBeLessThan 15
        //                               | subject is now of type Int (actually 10)


        fails { // because sub-expectation fails
            expect(Result.success(10)) toBe aSuccess toBeLessThan 5 toBeGreaterThan 12
            //                               |          |           | not evaluated/reported because `toBeLessThan` already fails
            //                               |          |           | use `toBe aSuccess { ... }` if you want that all expectations are evaluated
            //                               |          | fails
            //                               | subject is now of type Int (actually 10)
        }

        fails { // because it was a Failure
            expect(
                Result.failure<Int>(ArithmeticException())
            ) toBe aSuccess toBeGreaterThan 12
            //      |           | not evaluated/reported because `toBeASuccess` already fails
            //      |           | use `toBe aSuccess { ... }` if you want that all expectations are evaluated
            //      | fails
        }
    }

    @Test
    fun toBeASuccess() {

        expect(Result.success(10)) toBe aSuccess { // subject within this expectation-group is of type Int (actually 10)
            it toEqual 10
            it toBeLessThan 15
        } // subject here is back to type Result<Int>

        fails { // because sub-expectation fails
            expect(Result.success(10)) toBe aSuccess {
                it toBeGreaterThan 15 // fails
                it toBeLessThan 5     // still evaluated even though `toBeGreaterThan` already fails
                //                       use `.toBeASuccess.` if you want a fail fast behaviour
            }
        }

        fails { // because it was a Failure, but since we use an expectation-group...
            expect(Result.failure<Int>(ArithmeticException())) toBe aSuccess {
                it toBeGreaterThan 12 // ...reporting mentions that subject was expected `to be greater than: 12`
                //                       use `.toBeASuccess.` if you want a fail fast behaviour
            }

        }
    }

    //TODO 1.1.0 activate once we have the workaround for #1234 implemented
//    @Test
//    fun toBeAFailureFeature() {
//        val message = "wrong argument"
//        val failure = Result.failure<Int>(IllegalArgumentException(message))
//
//        expect(failure)
//            .toBeAFailure<IllegalArgumentException>() messageToContain "argument"
//        //      | subject is now of type IllegalArgumentException
//
//
//        fails { // because sub-expectation fails
//            expect(failure).toBeAFailure<IllegalArgumentException>() messageToContain "parameter"
//        }
//
//        fails { // because wrong Expectation type expected
//            expect(failure)
//                .toBeAFailure<ArithmeticException>() messageToContain "parameter"
//            //     |                                  | not evaluated/reported because toBeAFailure already fails
//            //     |                                  | use `toBeAFailure<...> { ... }` if you want that all expectations are evaluated
//            //     | fails
//        }
//
//        fails { // because it was a Success
//            expect(Result.success(10))
//                .toBeAFailure<IllegalArgumentException>() messageToContain "parameter"
//            //       |                                      | not evaluated/reported because toBeAFailure already fails
//            //       |                                      | use `toBeAFailure<...> { ... }` if you want that all expectations are evaluated
//            //       | fails
//        }
//    }
//
//    @Test
//    fun toBeAFailure() {
//        val errorMessage = "can not divide by zero"
//        val failure = Result.failure<Int>(ArithmeticException(errorMessage))
//
//        expect(failure).toBeAFailure<ArithmeticException> {  // subject within this expectation-group is of type ArithmeticException
//            messageToContain("by zero")
//        } // subject here is back to type Result<Int>
//
//        fails { // because sub-expectation fails
//            expect(failure).toBeAFailure<IllegalArgumentException> {
//                its messageToContain "parameter" // fails
//                its.message toStartWith "wrong"  // still evaluated even though messageToContain already fails
//                //                                  use `.toBeAFailure.` if you want a fail fast behaviour
//            }
//        }
//
//        fails { // because wrong Expectation type expected, but since we use an expectation-group...
//            expect(failure).toBeAFailure<ArithmeticException> {
//                its messageToContain "parameter" // ...reporting mentions that subject's message was expected `to contain: "parameter"``
//                //                                  use `.toBeAFailure.` if you want a fail fast behaviour
//            }
//        }
//
//        fails { // because it was a Success, but since we use a block
//            expect(Result.success(10)).toBeAFailure<IllegalArgumentException> {
//                its messageToContain "parameter" // ...reporting mentions that subject's message was expected `to contain: "parameter"``
//                //                                  use `.toBeAFailure.` if you want a fail fast behaviour
//            }
//        }
//    }
}
