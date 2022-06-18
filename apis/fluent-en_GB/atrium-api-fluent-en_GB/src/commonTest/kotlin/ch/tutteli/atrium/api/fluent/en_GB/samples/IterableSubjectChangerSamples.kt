package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.asList
import ch.tutteli.atrium.api.fluent.en_GB.toContain
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class IterableSubjectChangerSamples {
    @Test
    fun asListFeature() {
        expect(0..2)
            .asList()  // subject is now of type List<Int>
            .toEqual(listOf(0, 1, 2))

        fails {
            expect(0..2)
                .asList()  // subject is now of type List<Int>
                .toContain(3)  // fails
                .toContain(4)  // not evaluated/reported because above `toContain` already fails
            //                    use `.asList { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun asList() {
        expect(0..2)  // subject within this expectation-group is of type List<Int>
            .asList {
                toEqual(listOf(0, 1, 2))
            }  // subject here is back to type IntRange

        fails {
            // all expectations inside an expectation-group are evaluated together; for more details see:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(0..2)
                .asList {
                    toContain(3)  // fails
                    toContain(4)  // still evaluated even though above `toContain` already fails
                    //               use `.asList().` if you want a fail fast behaviour
                }
        }
    }
}
