package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class MapFeatureExtractorSamples {

    @Test
    fun getExistingFeature() {
        expect(mapOf(1 to "a"))
            .getExisting(1) // subject is of type String (actually "a")
            .toEqual("a")

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(1)  // subject is of type String (actually "a")
                .toContain("b")    // fails
                .toStartWith("z")  // not
        }

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(2) // expectation fails because key 2 does not exist
                .toEqual("a")   // not reported even though `getExisting` already fails
            //                     use `.getExisting(key) { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun getExisting() {
        expect(mapOf(1 to "a"))
            .getExisting(1) {   // subject inside this block is of type String (actually "a")
                toEqual("a")
            } // subject here is back to type Map<Int, String>

        fails {
            expect(mapOf(1 to "a"))
                .getExisting(1) {    // subject inside this block is of type String (actually "a")
                    toEqual("b")     // fails because "a" is not equal to "b"
                    toStartWith("z") // still evaluated because we use an expectation group block
                    //                  use `.getExisting(key).` if you want a fail fast behaviour
                }
        }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(mapOf(1 to "a"))
                .getExisting(2) {   // fails because key 2 does not exist
                    toEqual("a")    // still evaluated because we use an expectation group block
                    //                 use `.getExisting(key).` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun keysFeature() {
        expect(mapOf(1 to "a"))
            .keys         // subject is now of type Set<Int> (containing 1)
            .toContain(1)

        fails {
            expect(mapOf(1 to "a"))
                .keys          // subject is now of type Set<Int> (containing 1)
                .toContain(2)  // fails
                .toHaveSize(5) // not reported even though `toContain` already fails
            //                    use `.keys { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun keys() {
        expect(mapOf(1 to "a"))
            .keys { // subject inside this block is of type Set<Int> (containing 1)
                toEqual(setOf(1))
            } // subject here is back to type Map<Int, String>

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(mapOf(1 to "a"))
                .keys {                // subject inside this block is of type Set<Int> (containing 1)
                    toEqual(setOf(2))  // fails because 1 is not equal to 2
                    toHaveSize(3)      // still evaluated because we use an expectation group block
                    //                    use `.keys.` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun valuesFeature() {
        expect(mapOf(1 to "a"))
            .values // subject is now of type Collection<String> (containing "a")
            .toContain("a")

        fails {
            expect(mapOf(1 to "a"))
                .values          // subject is now of type Collection<String> (containing "a")
                .toContain("b")  // fails because "a" is not equal to "b"
                .toHaveSize(5)   // not reported even though `toContain` already fails
            //                      use `.values { ... }` if you want that all expectations are evaluated
        }
    }

    @Test
    fun values() {
        expect(mapOf(1 to "a"))
            .values {   // subject inside this block is of type Collection<String> (containing "a")
                toEqual(setOf("a"))
            }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(mapOf(1 to "a"))
                .values { // subject inside this block is of type Collection<String> (containing <"a">)
                    toEqual(setOf("b"))    // fails because "a" is not equal to "b"
                    //                        use `.values.` if you want a fail fast behaviour
                }
        }
    }

    @Test
    fun sizeFeature() {
        expect(mapOf(1 to "a", 2 to "b"))
            .size // subject is now of type Int (containing 2)
            .toEqual(2)

        fails {
            expect(mapOf(1 to "a", 2 to "b"))
                .size          // subject is now of type Int (containing 2)
                .toBeGreaterThan(5)  // fails because 2 is not greater than 5
                .notToEqual(2)   // not reported even though `toBeGreaterThan` already fails
            //                      use `.size { ... }` if you want that all expectations are evaluated
        }
    }


    @Test
    fun size() {
        expect(mapOf(1 to "a", 2 to "b"))
            .size {   // subject inside this block is of type Int (containing 2)
                toEqual(2)
            }

        fails {
            // all expectations are evaluated inside an expectation group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(mapOf(1 to "a", 2 to "b"))
                .size { // subject inside this block is of type Int (containing 2)
                    toEqual(5)    // fails because 5 is not equal to 2
                    toBeLessThan(1)    // fails because 2 is not less than 1
                    //                        use `.size.` if you want a fail fast behaviour
                }
        }
    }
}
