package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.asList
import ch.tutteli.atrium.api.infix.en_GB.it
import ch.tutteli.atrium.api.infix.en_GB.o
import ch.tutteli.atrium.api.infix.en_GB.toContain
import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class IterableSubjectChangerSamples {
    @Test
    fun asListFeature() {
        expect(0..2) asList o toEqual listOf(0, 1, 2)
        //              | subject is now of type List<Int>

        fails {
            expect(0..2) asList o toContain 3 toContain 4
        }
    }

    @Test
    fun asList() {
        expect(0..2).asList { // subject within this expectation-group is of type List<Int>
            it toEqual listOf(0, 1, 2)
        }  // subject here is back to type IntRange

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(0..2).asList {
                it toContain 3  // fails
                it toContain 4  // still evaluated even though above `toContain` already fails
                //                 use `asList o` if you want a fail fast behaviour
            }
        }
    }
}
