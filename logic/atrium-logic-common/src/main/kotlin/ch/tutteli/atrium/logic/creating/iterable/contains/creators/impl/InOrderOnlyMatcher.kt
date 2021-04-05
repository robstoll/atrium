package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.*
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.translations.DescriptionIterableAssertion

interface InOrderOnlyMatcher<E, SC> {
    fun AssertionContainer<List<E>>.elementAssertionCreator(maybeElement: Option<E>, searchCriterion: SC): Assertion

    fun AssertionContainer<List<E>>.addSingleEntryAssertion(
        currentIndex: Int,
        searchCriterion: SC,
        translatableIndex: DescriptionIterableAssertion
    ) {
        val maybeElement = maybeSubject.flatMap { list ->
            if (currentIndex < list.size) Some(list[currentIndex]) else None
        }
        val elementAssertion = elementAssertionCreator(maybeElement, searchCriterion)
        val assertion = maybeElement.map { elementAssertion }.getOrElse {
            //TODO 0.18.0: extract common pattern
            maybeSubject.fold({
                // already in an explanatory assertion context, no need to wrap it again
                elementAssertion
            }) {
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .withAssertion(elementAssertion)
                    .failing
                    .build()
            }
        }

        addAssertion(
            assertionBuilder.feature
                .withDescriptionAndRepresentation(
                    TranslatableWithArgs(translatableIndex, currentIndex),
                    maybeElement.getOrElse { DescriptionIterableAssertion.SIZE_EXCEEDED }
                )
                .withAssertion(assertion)
                .build()
        )
    }

}
