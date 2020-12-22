package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.translations.DescriptionComparableAssertion
import kotlin.test.Test

class ListAssertionSamples {
    private val isLessThanDescr = DescriptionComparableAssertion.IS_LESS_THAN.getDefault()
    private val isGreaterThanDescr = DescriptionComparableAssertion.IS_GREATER_THAN.getDefault()

    @Test
    fun getFeature() {
        val list = listOf(1, 2, 3)

        expect(list) get 0 isLessThan 2
        expect(list) get 0 isGreaterThan 0

        fails {
            expect(list) get 3 isLessThan 0
        } message {
            contains("index out of bounds")
            containsNot("is less than: 2")
        }

        fails {
            expect(list) get 0 isGreaterThan 2
            expect(list) get 0 isLessThan 0
        } message {
            contains("${isGreaterThanDescr}: 2")
            containsNot("${isLessThanDescr}: 0")
        }
    }

    @Test
    fun get() {
        val list = listOf(1, 2, 3)
        expect(list) get index(0) {
            isLessThan(2)
            isGreaterThan(0)
        }
        expect(list) get index(1) {
            isLessThan(3)
            isGreaterThan(1)
        }

        fails {
            expect(list) get index(0) {
                isGreaterThan(2)
                isLessThan(0)
            }
        } messageContains values(
            "${isGreaterThanDescr}: 2",
            "${isLessThanDescr}: 0"
        )
    }
}
