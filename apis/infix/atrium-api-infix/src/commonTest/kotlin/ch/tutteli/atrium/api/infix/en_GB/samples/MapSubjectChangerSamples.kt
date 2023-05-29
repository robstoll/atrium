package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapSubjectChangerSamples {

    @Test
    fun asEntriesFeature() {
        expect(mapOf(1 to "a")) asEntries o toEqual mapOf(1 to "a").entries

        fails {
            // because <1,"a"> is not equal to <1,"b">
            expect(mapOf(1 to "a")) asEntries o toEqual mapOf(1 to "b").entries
        }
    }

    @Test
    fun asEntries() {
        expect(mapOf(1 to "a")) asEntries { // subject inside this expectation-group is of type Map.Entry<Int, String> (actually <1,"a">)
            this toEqual mapOf(1 to "a").entries
        }

        fails {
            expect(mapOf(1 to "a")) asEntries {   // subject inside this expectation-group is of type Map.Entry<Int, String> (actually <1,"a">)
                this toEqual mapOf(1 to "b").entries  // fails because <1,"a"> is not equal to <1,"b">
            }
        }
    }
}
