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
            .toEqual(10)
            .toBeLessThan(15)

        fails { // because sub-expectation fails
            expect(Result.success(10))
                .toBeASuccess()      // subject is now of type Int (actually 10)
                .toBeLessThan(5)     // fails
                .toBeGreaterThan(12) // not evaluated/reported because `toBeLessThan` already fails
            //                          use `.toBeASuccess { ... }` if you want that all expectations are evaluated
        }

        fails { // because it was a Failure
            expect(Result.failure<Int>(ArithmeticException()))
                .toBeASuccess()      // fails
                .toBeGreaterThan(12) // not evaluated/reported because `toBeASuccess` already fails
            //                          use `.toBeASuccess { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun toBeASuccess() {

        expect(Result.success(10)).toBeASuccess { // subject within this expectation-group is of type Int (actually 10)
            toEqual(10)
            toBeLessThan(15)
        } // subject here is back to type Result<Int>

        fails { // because sub-expectation fails
            expect(Result.success(10)).toBeASuccess {
                toBeGreaterThan(15) // fails
                toBeLessThan(5)     // still evaluated even though `toBeGreaterThan` already fails
                //                     use `.toBeASuccess.` if you want a fail fast behaviour
            }
        }

        fails { // because it was a Failure, but since we use an expectation-group...
            expect(Result.failure<Int>(ArithmeticException())).toBeASuccess {
                toBeGreaterThan(12) // ...reporting mentions that subject was expected `to be greater than: 12`
                //                     use `.toBeASuccess.` if you want a fail fast behaviour
            }

        }
    }





}
