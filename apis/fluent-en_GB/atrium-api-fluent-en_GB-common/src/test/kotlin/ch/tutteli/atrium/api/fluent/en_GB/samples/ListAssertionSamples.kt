package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class ListAssertionSamples {

    @Test
    fun getFeature() {
        val list = listOf(1, 2, 3)

        expect(list)
            .get(0)                   // subject of the assertion is now 1
            .isLessThan(2)   // subject of the assertion is still 1
            .isGreaterThan(0)

        fails {
            // index is out of bound
            expect(list)
                .get(3)
                .isLessThan(0) // not reported, because 'get(3)' fails with "index out of bounds"
        }.message {
            contains("index out of bounds")
            containsNot("is less than: 2")
        }

        fails {
            expect(list)
                .get(0)
                .isGreaterThan(2)
                .isLessThan(0) // not reported, use `get(index) { ... }` with valid index if you want that all assertions are evaluated
        }.message {
            contains("is greater than: 2")
            containsNot("is less than: 0")
        }
    }

    @Test
    fun get() {
        val list = listOf(1, 2, 3)

        expect(list)
            .get(0) { // subject inside this block is now 1
                isLessThan(2)
                isGreaterThan(0)
            } // subject here is still list
            .get(1) { // subject inside this block is now 2 because we passed 1 as an index
                isLessThan(3)
                isGreaterThan(1)
            }

        fails {
            // all assertions are evaluated inside an assertion group block; for more details:
            // https://github.com/robstoll/atrium#define-single-assertions-or-assertion-groups

            expect(list)
                .get(0) {
                    isGreaterThan(2)
                    isLessThan(0)    // still evaluated even though isGreaterThan(2) already fails,
                    // use the `.get(index).` if you want a fail fast behaviour
                }
        }.messageContains("is greater than: 2", "is less than: 0")
    }
}
