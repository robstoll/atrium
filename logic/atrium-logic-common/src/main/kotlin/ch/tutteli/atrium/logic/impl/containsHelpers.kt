package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.collectBasedOnSubject
import ch.tutteli.atrium.logic.creating.collectors.collectAssertions
import ch.tutteli.atrium.logic.hasNext
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionIterableAssertion
import ch.tutteli.kbox.WithIndex
import ch.tutteli.kbox.identity
import ch.tutteli.kbox.mapWithIndex

internal fun <E : Any> allCreatedAssertionsHold(
    container: AssertionContainer<*>,
    subject: E?,
    assertionCreator: (Expect<E>.() -> Unit)?
): Boolean = when (subject) {
    null -> assertionCreator == null
    else -> assertionCreator != null && container.collectBasedOnSubject(Some(subject), assertionCreator).holds()
}

internal fun <E : Any> createExplanatoryAssertionGroup(
    container: AssertionContainer<*>,
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?
): AssertionGroup {
    return assertionBuilder.explanatoryGroup
        .withDefaultType
        .let {
            //TODO 0.18.0 looks a lot like toBeNullIfNullGiven
            if (assertionCreatorOrNull != null) {
                // we don't use a subject, we will not show it anyway
                it.collectAssertions(container, None, assertionCreatorOrNull)
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

internal fun <E> createIndexAssertions(
    list: List<E>,
    predicate: (WithIndex<E>) -> Boolean
) = list
    .asSequence()
    .mapWithIndex()
    .filter { predicate(it) }
    .map { (index, element) ->
        assertionBuilder.createDescriptive(
            TranslatableWithArgs(DescriptionIterableAssertion.INDEX, index),
            element,
            falseProvider
        )
    }
    .toList()

internal fun createExplanatoryGroupForMismatches(
    mismatches: List<Assertion>
): AssertionGroup {
    return assertionBuilder.explanatoryGroup
        .withWarningType
        .withAssertion(
            assertionBuilder.list
                .withDescriptionAndEmptyRepresentation(DescriptionIterableAssertion.WARNING_MISMATCHES)
                .withAssertions(mismatches)
                .build()
        )
        .failing
        .build()
}

internal fun createAssertionGroupFromListOfAssertions(
    description: Translatable,
    representation: Any?,
    assertions: List<Assertion>
): AssertionGroup =
    if (assertions.isEmpty())
        assertionBuilder.invisibleGroup
            .withAssertion(
                assertionBuilder.createDescriptive(description, representation, trueProvider)
            ).build()
    else assertionBuilder.list
        .withDescriptionAndRepresentation(description, representation)
        .withAssertions(assertions)
        .build()

internal fun <E> decorateAssertionWithHasNext(
    assertion: AssertionGroup,
    listAssertionContainer: AssertionContainer<List<E>>
): AssertionGroup {
    val hasNext = listAssertionContainer.hasNext(::identity)
    return if (hasNext.holds()) {
        assertion
    } else {
        assertionBuilder.invisibleGroup
            .withAssertions(
                hasNext,
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withAssertion(assertion)
                    .build()
            )
            .build()
    }
}

