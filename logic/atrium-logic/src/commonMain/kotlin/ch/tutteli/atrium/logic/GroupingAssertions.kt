@file:Suppress("ObjectPropertyName", "FunctionName")

package ch.tutteli.atrium.logic

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping

/**
 * @since 1.1.0
 */
interface GroupingAssertions {

    fun <T> grouping(
        container: AssertionContainer<T>, description: String, representationProvider: () -> Any?,
        groupingActions: ExpectGrouping.() -> Unit
    ): Assertion

    fun <T> group(
        container: AssertionContainer<T>, description: String, representationProvider: () -> Any?,
        assertionCreator: Expect<T>.() -> Unit
    ): Assertion

}
