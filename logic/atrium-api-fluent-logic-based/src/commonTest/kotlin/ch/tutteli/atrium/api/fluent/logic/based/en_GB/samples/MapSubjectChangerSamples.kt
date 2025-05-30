package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapSubjectChangerSamples {

    @Test
    fun asEntriesFeature() {
        expect(mapOf(1 to "a"))
            .asEntries() // subject is now of type Map.Entry<Int, String> (actually <1,"a">)
            .toEqual(mapOf(1 to "a").entries)

        fails { // because <1,"a"> is not equal to <1,"b">
            expect(mapOf(1 to "a")).asEntries()
                .toEqual(mapOf(1 to "b").entries)
        }
    }

    @Test
    fun asEntries() {
        expect(mapOf(1 to "a")).asEntries { // subject inside this expectation-group is of type Map.Entry<Int, String> (actually <1,"a">)
            toEqual(mapOf(1 to "a").entries)
        } // subject here is back to type Map<Int, String>

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(mapOf(1 to "a")).asEntries {   // subject inside this expectation-group is of type Map.Entry<Int, String> (actually <1,"a">)
                toEqual(mapOf(1 to "b").entries)  // fails because <1,"a"> is not equal to <1,"b">
            }
        }
    }
}
