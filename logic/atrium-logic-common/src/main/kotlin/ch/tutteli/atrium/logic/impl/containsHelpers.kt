package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

internal fun createHasElementAssertion(iterator: Iterator<*>): AssertionGroup {
    val hasElement = iterator.hasNext()
    return assertionBuilder.feature
        .withDescriptionAndRepresentation(DescriptionIterableAssertion.HAS_ELEMENT, Text(hasElement.toString()))
        .withAssertion(
            assertionBuilder.createDescriptive(DescriptionBasic.IS, Text(true.toString())) { hasElement }
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
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    list: List<E?>
): AssertionGroup = createExplanatoryAssertionGroup(assertionCreatorOrNull) {
    list.asSequence().filterNotNull().map { Some(it) }.firstOrNull() ?: None
}

internal inline fun <E : Any> createExplanatoryAssertionGroup(
    noinline assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    firstOrNull: () -> Option<E>
): AssertionGroup {
    return assertionBuilder.explanatoryGroup
        .withDefaultType
        .let {
            if (assertionCreatorOrNull != null) {
                it.collectAssertions(firstOrNull(), assertionCreatorOrNull)
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

internal fun createEntryAssertion(explanatoryGroup: AssertionGroup, found: Boolean): AssertionGroup {
    return assertionBuilder.fixedClaimGroup
        .withListType
        .withClaim(found)
        .withDescriptionAndEmptyRepresentation(DescriptionIterableAssertion.AN_ENTRY_WHICH)
        .withAssertion(explanatoryGroup)
        .build()
}
