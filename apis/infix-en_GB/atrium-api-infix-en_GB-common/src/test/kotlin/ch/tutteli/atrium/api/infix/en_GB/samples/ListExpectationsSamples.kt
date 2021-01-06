package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import kotlin.test.Test

class ListExpectationsSamples {
    private val isLessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
    private val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()

    @Test
    fun getFeature() {
        val list = listOf(1, 2, 3)

        expect(list) get 0 isLessThan 2 isGreaterThan 0
        //            |                     | subject is still of type Int (still 1)
        //            |        | subject is still of type Int (still 1)
        //            | subject is now of type Int (actually 1)

        fails {
            expect(list) get 3 isLessThan 0
            //             |        | not reported
            //             | fails because index 3 is out of bound
            // use `get index(elementIndex) { ... }` if you want that all assertions are evaluated
        } message {
            contains("index out of bounds")
            containsNot("is less than: 2")
        }

        fails {
            expect(list) get 0 isGreaterThan 2 isLessThan 0
            //            |        |            | not reported because `isGreaterThan 2` already fails
            //            |        | fails
            //            | subject is now of type Int (actually 1)
            expect(list) get 0 isLessThan 0
            // use `get index(elementIndex) { ... }` if you want that all assertions are evaluated

        } message {
            contains("${isGreaterThanDescr}: 2")
            containsNot("${isLessThanDescr}: 0")
        }
    }

    @Test
    fun get() {
        val list = listOf(1, 2, 3)

        expect(list) get index(0) {           // subject inside this block is of type Int (actually 1)
            it isLessThan 2
            it isGreaterThan 0
        }

        expect(list) get index(1) {           // subject inside this block is of type Int (actually 2)
            it isLessThan 3
            it isGreaterThan 1
        }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(list) get index(0) {
                it isGreaterThan 2           // fails
                it isLessThan 0              // still evaluated even though `isGreaterThan(2)` already fails,
                // use the ` get elementIndex ` if you want a fail fast behaviour
            }
        } messageContains values(
            "${isGreaterThanDescr}: 2",
            "${isLessThanDescr}: 0"
        )
    }
}
