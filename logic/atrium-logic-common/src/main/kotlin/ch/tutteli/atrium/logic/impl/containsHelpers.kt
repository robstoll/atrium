package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

internal fun createHasElementAssertion(list: List<*>): Assertion {
    return assertionBuilder.feature
        .withDescriptionAndRepresentation(DescriptionIterableAssertion.HAS_ELEMENT, Text(list.isNotEmpty().toString()))
        .withAssertion(
            assertionBuilder.createDescriptive(DescriptionBasic.IS, Text(true.toString())) { list.isNotEmpty() }
        )
        .build()
}

internal fun <E : Any> allCreatedAssertionsHold(
    subject: E?,
    assertionCreator: (Expect<E>.() -> Unit)?
): Boolean = when (subject) {
    null -> assertionCreator == null
    else -> assertionCreator != null && assertionCollector.collect(Some(subject), assertionCreator).holds()
}


internal fun <E : Any> createExplanatoryAssertionGroup(
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): AssertionGroup {
    return assertionBuilder.explanatoryGroup
        .withDefaultType
        .let {
            if (assertionCreatorOrNull != null) {
                // we don't use a subject, we will not show it anyway
                it.collectAssertions(None, assertionCreatorOrNull)
            } else {
                it.withAssertion(
                    // it is for an explanatoryGroup where it does not matter if the assertion holds or not
                    // thus it is OK to use trueProvider
                    assertionBuilder.createDescriptive(DescriptionBasic.IS, Text.NULL, trueProvider)
                )
            }
        }
        .build()
}
