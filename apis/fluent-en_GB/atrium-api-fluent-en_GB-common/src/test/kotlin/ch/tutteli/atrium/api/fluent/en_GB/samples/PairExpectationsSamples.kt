package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class PairAssertionSamples {

    @Test
    fun firstFeature() {
        val pair = 1 to "one"

        expect(pair)
            .first           // subject is now of type Int (actually 1)
            .isLessThan(2)   // subject is still of type Int (still 1)
            .isGreaterThan(0)

        fails {
            expect(pair)
                .first
                .isGreaterThan(2) // fails
                .isLessThan(0)    // not reported because `isGreaterThan(2)` already fails
                                  // use `first { ... }` if you want that all assertions are evaluated
        }.message {
            contains("is greater than: 2")
            containsNot("is less than: 0")
        }
    }

    @Test
    fun first() {
        val pair = 1 to "one"

        expect(pair)
            .first { // subject inside this block is of type Int (actually 1)
                isLessThan(2)
            } // subject here is back to type Pair<Int, String>
            .first {
                isGreaterThan(0)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(pair)
                .first {
                    isGreaterThan(2) // fails
                    isLessThan(0)    // still evaluated even though `isGreaterThan(2)` already fails
                                     // use `.first.` if you want a fail fast behaviour
                }
        }.messageContains(
            "is greater than: 2",
            "is less than: 0"
        )
    }

    @Test
    fun secondFeature() {
        val pair = "one" to 1

        expect(pair)
            .second           // subject now of type Int (actually 1)
            .isLessThan(2)    // subject is still of type Int (still 1)
            .isGreaterThan(0)

        fails {
            expect(pair)
                .second
                .isGreaterThan(2)    // fails
                .isLessThan(0)       // not reported because `isGreaterThan(2)` already fails
                                     // use `second { ... }` if you want that all assertions are evaluated
        }.message {
            contains("is greater than: 2")
            containsNot("is less than: 0")
        }
    }

    @Test
    fun second() {
        val pair = "one" to 1

        expect(pair)
            .second { // subject inside this block is of type Int (actually 1)
                isLessThan(2)
            } // subject here is back to type Pair<Int, String>
            .second {
                isGreaterThan(0)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(pair)
                .second {
                    isGreaterThan(2) // fails
                    isLessThan(0)    // still evaluated even though `isGreaterThan(2)` already fails,
                                     // use `.second.` if you want a fail fast behaviour
                }
        }.messageContains(
            "is greater than: 2",
            "is less than: 0"
        )
    }
}
