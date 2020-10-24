package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class PairAssertionSamples {

    @Test
    fun firstFeature() {
        val pair = 1 to "one"

        expect(pair)
            .first           // subject of the assertion is now 1
            .isLessThan(2)   // subject of the assertion is still 1
            .isGreaterThan(0)

        fails {
            expect(pair)
                .first
                .isGreaterThan(2)
                .isLessThan(0)       // not reported, use `first { ... }` if you want that all assertions are evaluated
        }.message {
            contains("is greater than: 2")
            containsNot("is less than: 0")
        }
    }

    @Test
    fun first() {
        val pair = 1 to "one"

        expect(pair)
            .first { //subject inside this block is now 1
                isLessThan(2)
            } // subject here is still pair
            .first {
                isGreaterThan(0)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(pair)
                .first {
                    isGreaterThan(2)
                    isLessThan(0)    // still evaluated even though isGreaterThan(2) already fails,
                    // use the `.first.` if you want a fail fast behaviour
                }
        }.messageContains("is greater than: 2", "is less than: 0")
    }

    @Test
    fun secondFeature() {
        val pair = "one" to 1

        expect(pair)
            .second           // subject of the assertion is now 1
            .isLessThan(2)   // subject of the assertion is still 1
            .isGreaterThan(0)

        fails {
            expect(pair)
                .second
                .isGreaterThan(2)
                .isLessThan(0)       // not reported, use `second { ... }` if you want that all assertions are evaluated
        }.message {
            contains("is greater than: 2")
            containsNot("is less than: 0")
        }
    }

    @Test
    fun second() {
        val pair = "one" to 1

        expect(pair)
            .second { //subject inside this block is now 1
                isLessThan(2)
            } // subject here is still pair
            .second {
                isGreaterThan(0)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(pair)
                .second {
                    isGreaterThan(2)
                    isLessThan(0)    // still evaluated even though isGreaterThan(2) already fails,
                    // use the `.second.` if you want a fail fast behaviour
                }
        }.messageContains("is greater than: 2", "is less than: 0")
    }
}
