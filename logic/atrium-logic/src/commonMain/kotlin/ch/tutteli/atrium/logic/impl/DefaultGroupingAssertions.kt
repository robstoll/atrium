package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DefaultGroupingAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.logic.GroupingAssertions
import ch.tutteli.atrium.logic.collectForComposition
import ch.tutteli.atrium.logic.toAssertionCreator

class DefaultGroupingAssertions : GroupingAssertions {

    override fun <T> grouping(
        container: AssertionContainer<T>,
        description: String,
        representationProvider: () -> Any?,
        groupingActions: ExpectGrouping.() -> Unit
    ): Assertion = group(container, description, representationProvider, groupingActions.toAssertionCreator())

    override fun <T> group(
        container: AssertionContainer<T>,
        description: String,
        representationProvider: () -> Any?,
        assertionCreator: Expect<T>.() -> Unit
    ): Assertion {
        val assertions = container.collectForComposition(assertionCreator)
        return assertionBuilder
            .customType(DefaultGroupingAssertionGroupType)
            .withDescriptionAndRepresentation(description, representationProvider)
            .withAssertions(assertions)
            .build()
    }

}
