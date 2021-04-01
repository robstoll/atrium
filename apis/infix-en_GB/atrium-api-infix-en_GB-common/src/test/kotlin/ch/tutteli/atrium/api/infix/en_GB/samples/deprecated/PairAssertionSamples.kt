//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.api.infix.en_GB.samples.deprecated

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.infix.en_GB.samples.fails
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import kotlin.test.Test

class PairAssertionSamples {
    private val isLessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
    private val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()

    @Test
    fun firstFeature() {
        val pair = 1 to "one"

        expect(pair)
            .first isLessThan 2 isGreaterThan 0
            //|         |             | subject is still of type Int (still 1)
            //|         | subject is still of type Int (still 1)
            //| subject is now of type Int (actually 1)

        fails {
            expect(pair)
                .first isGreaterThan 2 isLessThan 0
                //|         |             | not reported because `isGreaterThan 2` already fails
                //|         | fails
                //| subject is now of type Int (actually 1)
                // use `.first { ... }` if you want that all assertions are evaluated
        } message {
            contains("${isGreaterThanDescr}: 2")
            containsNot("${isLessThanDescr}: 0")
        }
    }

    @Test
    fun first() {
        val pair = 1 to "one"

        expect(pair)
            .first { // subject inside this block is of type Int (actually 1)
                it isLessThan 2
            } // subject here is back to type Pair<Int, String>
            .first { // subject inside this block is of type Int (actually 1)
                it isGreaterThan 0
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(pair)
                .first { // subject inside this block is of type Int (actually 1)
                    it isGreaterThan 2 //fails
                    it isLessThan 0    // still evaluated even though `isGreaterThan(2)` already fails
                                       // use `.first.` if you want a fail fast behaviour
                }
        } messageContains values(
            "${isGreaterThanDescr}: 2",
            "${isLessThanDescr}: 0"
        )
    }

    @Test
    fun secondFeature() {
        val pair = "one" to 1

        expect(pair)
            .second isLessThan 2 isGreaterThan 0
            //|         |             | subject is still of type Int (still 1)
            //|         | subject is still of type Int (still 1)
            //| subject is now of type Int (actually 1)

        fails {
            expect(pair)
                .second isGreaterThan 2 isLessThan 0
                //|         |             | not reported because `isGreaterThan 2` already fails
                //|         | fails
                //| subject is now of type Int (actually 1)
                // use `.second { ... }` if you want that all assertions are evaluated
        } message {
            contains("${isGreaterThanDescr}: 2")
            containsNot("${isLessThanDescr}: 0")
        }
    }

    @Test
    fun second() {
        val pair = "one" to 1

        expect(pair)
            .second { // subject inside this block is of type Int (actually 1)
                it isLessThan 2
            } // subject here is back to type Pair<Int, String>
            .second { // subject inside this block is of type Int (actually 1)
                it isGreaterThan 0
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups
            expect(pair)
                .second { // subject inside this block is of type Int (actually 1)
                    it isGreaterThan 2 // fails
                    it isLessThan 0    // still evaluated even though `isGreaterThan 2` already fails,
                                       // use `.second.` if you want a fail fast behaviour
                }
        } messageContains values(
            "${isGreaterThanDescr}: 2",
            "${isLessThanDescr}: 0"
        )
    }
}
