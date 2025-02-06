package ch.tutteli.atrium.api.fluent.logic.based.en_GB.samples

import ch.tutteli.atrium.api.fluent.logic.based.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapEntryFeatureExtractorSamples {

    @Test
    fun keyFeature() {
        val entry = mapOf(1 to "a").entries.first()

        expect(entry)
            .key          // subject here is of type Int (actually 1)
            .toEqual(1)

        fails {
            expect(entry)
                .key             // subject here is of type Int (actually 1)
                .toEqual(2)      // fails
                .toBeLessThan(0) // not evaluated/reported because `toEqual` already fails
            //                      use `.key { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun key() {
        val entry = mapOf(1 to "a").entries.first()

        expect(entry).key {  // subject inside this expectation-group is of type Int (actually 1)
            toEqual(1)
        } // subject here is back to type Map.Entry<Int, String>

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(entry).key {  // subject inside this expectation-group is of type Int (actually 1)
                toEqual(2)       // fails
                toBeLessThan(0)  // still evaluated even though `toEqual` already fails,
                //                  use `.key.` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun valueFeature() {
        val entry = mapOf(1 to "a").entries.first()

        expect(entry)
            .value  // subject here is of type String (actually "a")
            .toEqual("a")

        fails {
            expect(entry)
                .value            // subject here is of type String (actually "a")
                .toEqual("b")     // fails
                .toStartWith("z") // not evaluated/reported because `toEqual` already fails
            //                       use `.value { ... }` if you want that all assertions are evaluated
        }
    }

    @Test
    fun value() {
        val entry = mapOf(1 to "a").entries.first()

        expect(entry).value { // subject inside this expectation-group is of type String (actually "a")
            toEqual("a")
        } // subject here is back to type Map.Entry<Int, String>

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(entry).value { // subject inside this expectation-group is of type String (actually "a")
                toEqual("b")      // fails
                toStartWith("z")  // still evaluated even though `toEqual` already fails,
                //                   use `.value.` if you want a fail fast behaviour
            }
        }
    }
}
