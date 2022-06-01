package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import java.util.*
import kotlin.test.Test

class OptionalExpectationSamples {

    @Test
    fun toBeEmpty() {

        expect(Optional.empty<String>()).toBeEmpty()

        fails {
            expect(Optional.of(1)).toBeEmpty()
        }
    }


    @Test
    fun toBePresentFeature() {
        expect(Optional.of(1))
            .toBePresent() // subject is now of type Int (actually 1)
            .toBeGreaterThan(0)


        fails { // because sub-expectation fails
            expect(Optional.of(10))
                .toBePresent()       // subject is now of type Int (actually 10)
                .toBeLessThan(5)     // fails
                .toBeGreaterThan(12) // not evaluated/reported because `toBeLessThan` already fails
            //                          use `.toBePresent { ... }` if you want that all expectations are evaluated
        }

        fails { // because it was empty
            expect(Optional.empty<Int>())
                .toBePresent()       // fails
                .toBeGreaterThan(0)  // not evaluated/reported because `toBePresent` already fails
            //                          use `.toBePresent { ... }` if you want that all expectations are evaluated
        }

    }

    @Test
    fun toBePresent() {

        expect(Optional.of(10)).toBePresent {  // subject within this expectation-group is of type Int (actually 10)
            toBeGreaterThan(0)
            toBeLessThan(11)
        }

        fails { // because sub-expectation fails
            expect(Optional.of(10)).toBePresent {
                toBeGreaterThan(15) // fails
                toBeLessThan(5)     // still evaluated even though `toBeGreaterThan` already fails
                //                     use `.toBePresent.` if you want a fail fast behaviour
            }
        }

        fails { // because it was empty, but since we use an expectation-group...
            expect(Optional.empty<Int>()).toBePresent {
                toBeGreaterThan(12) // ...reporting mentions that subject was expected `to be greater than: 12`
                //                     use `.toBePresent.` if you want a fail fast behaviour
            }

        }
    }
}
