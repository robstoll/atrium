package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import kotlin.test.Test

class MapFeatureExtractorSamples {

    @Test
    fun getExistingFeature() {
        expect(mapOf(1 to "a")) getExisting 1 toEqual "a"
        //                      | subject is of type String (actually "a")

        fails {
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")) getExisting 1 toEqual "b"
            //                      | subject is of type String (actually "a")
        }

        fails {
            // expectation fails because key 2 does not exist
            expect(mapOf(1 to "a")) getExisting 2 toEqual "a" // not reported, use `getExisting(key) { ... }`
            // if you want that all expectations are evaluated
        }
    }

    @Test
    fun getExisting() {
        expect(mapOf(1 to "a")) getExisting key(1) {   // subject inside this expectation-group is of type String (actually "a")
            this toEqual "a"
        }

        fails {
            expect(mapOf(1 to "a")) getExisting key(1) {  // subject inside this expectation-group is of type String (actually "a")
                this toEqual "b"   // fails because "a" is not equal to "b"
            }
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(mapOf(1 to "a")) getExisting key(2) {   // fails because key 2 does not exist
                this toEqual "a"    // still evaluated because we use an expectation-group block
                // use `.getExisting(key).` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun keysFeature() {
        expect(mapOf(1 to "a")).keys toContain 1
        //                     | subject is now of type Set<Int> (containing 1)

        fails {
            // fails because 1 is not equal to 2
            expect(mapOf(1 to "a")).keys toContain 2
            //                     | subject is now of type Set<Int> (containing 1)
        }
    }

    @Test
    fun keys() {
        expect(mapOf(1 to "a")) keys { // subject inside this expectation-group is of type Set<Int> (containing 1)
            this toEqual setOf(1)
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(mapOf(1 to "a")) keys { // subject inside this expectation-group is of type Set<Int> (containing 1)
                this toEqual setOf(2)      // fails because 1 is not equal to 2
                this toHaveSize 3          // still evaluated because we use an expectation-group block
                // use `.keys.` if you want a fail fast behaviour
            }
        }
    }

    @Test
    fun valuesFeature() {
        expect(mapOf(1 to "a")).values toContain "a"
        //                     | subject is now of type Collection<String> (containing "a")

        fails {
            // fails because "a" is not equal to "b"
            expect(mapOf(1 to "a")).values toContain "b"
            //                     | subject is now of type Collection<String> (containing "a")
        }
    }

    @Test
    fun values() {
        expect(mapOf(1 to "a")) values {   // subject inside this expectation-group is of type Collection<String> (containing "a")
            this toEqual setOf("a")
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(mapOf(1 to "a")) values { // subject inside this expectation-group is of type Collection<String> (containing <"a">)
                this toEqual setOf("b")      // fails because "a" is not equal to "b"
                // use `.values.` if you want a fail fast behaviour
            }
        }
    }

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

    @Test
    fun sizeFeature() {
        expect(mapOf(1 to "a", 2 to "b")).size toEqual 2
        //                               | subject is now of type Int (containing 2)

        fails {
            // fails because 1 is not equal to 2
            expect(mapOf(1 to "a")).size toEqual 2
            //                     | subject is now of type Int (containing 1)
        }
    }

    @Test
    fun size() {
        expect(mapOf(1 to "a", 2 to "b")) size {   // subject inside this expectation-group is of type Int (containing 2)
            this toEqual 2
            this toBeGreaterThan 1
        }

        fails {
            // all expectations are evaluated inside an expectation-group block; for more details:
            // https://github.com/robstoll/atrium#define-single-expectations-or-an-expectation-group

            expect(mapOf(1 to "a")) size { // subject inside this expectation-group is of type Int (containing 1)
                this toEqual 2      // fails because 1 is not equal to 2
                this toBeLessThan 0      // fails because 1 is not less than 0
                // use `.size.` if you want a fail fast behaviour
            }
        }
    }

}
