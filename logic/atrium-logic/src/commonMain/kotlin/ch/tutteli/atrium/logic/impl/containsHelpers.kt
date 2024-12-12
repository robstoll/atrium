//TODO 2.0.0 remove file
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.assertions.builders.withExplanatoryAssertion
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.collectBasedOnSubject
import ch.tutteli.atrium.logic.creating.collectors.collectAssertions
import ch.tutteli.atrium.logic.hasNext
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.translations.DescriptionAnyExpectation.TO_EQUAL
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation
import ch.tutteli.kbox.identity

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
    assertionCreatorOrNull: (Expect<E>.() -> Unit)?,
    maybeSubject: Option<E> = None
): AssertionGroup = assertionBuilder
    .explanatoryGroup
    .withDefaultType
    .let {
        // we don't use toEqualNullIfNullGivenElse because we want to report an empty assertionCreatorOrNull and
        // since we use None as subject and are already inside an explanatory assertion group, it would not be reported
        if (assertionCreatorOrNull != null) {
            it.collectAssertions(container, maybeSubject, assertionCreatorOrNull)
        } else {
            it.withAssertion(
                // it is for an proofExplanation where it does not matter if the assertion holds or not
                // thus it is OK to use trueProvider
                assertionBuilder.createDescriptive(TO_EQUAL, Text.NULL, trueProvider)
            )
        }
    }
    .build()

//TODO 1.3.0 replace with Representable and remove suppression
@Suppress("DEPRECATION")
internal fun <E> createIndexAssertions(
    list: List<E>,
    predicate: (IndexedValue<E>) -> Boolean
) = list
    .asSequence()
    .withIndex()
    .filter { predicate(it) }
    .map { (index, element) ->
        assertionBuilder.createDescriptive(
            ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(DescriptionIterableLikeExpectation.INDEX, index),
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
                .withDescriptionAndEmptyRepresentation(DescriptionIterableLikeExpectation.WARNING_MISMATCHES)
                .withAssertions(mismatches)
                .build()
        )
        .failing
        .build()
}

//TODO 1.3.0 remove suppress again, use InlineElement instead
@Suppress("DEPRECATION")
internal fun createAssertionGroupFromListOfAssertions(
    description: ch.tutteli.atrium.reporting.translating.Translatable,
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

//TODO 1.3.0 replace with Representable and remove suppression
@Suppress("DEPRECATION")
internal fun <E> decorateWithHintUseNotToHaveElementsOrNone(
    assertion: AssertionGroup,
    listAssertionContainer: AssertionContainer<List<E>>,
    notToHaveNextOrNoneFunName: String
): AssertionGroup {
    val hasNext = listAssertionContainer.hasNext(::identity)
    return if (!hasNext.holds()) {
        assertionBuilder.invisibleGroup
            .withAssertions(
                assertion,
                assertionBuilder.explanatoryGroup
                    .withHintType
                    .withExplanatoryAssertion(
                        ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(
                            DescriptionIterableLikeExpectation.USE_NOT_TO_HAVE_ELEMENTS_OR_NONE,
                            notToHaveNextOrNoneFunName
                        )
                    )
                    .build()
            )
            .build()
    } else {
        assertion
    }
}
