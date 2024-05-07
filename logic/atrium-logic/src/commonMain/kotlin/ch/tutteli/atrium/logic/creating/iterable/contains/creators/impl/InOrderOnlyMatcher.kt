package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.translations.DescriptionIterableLikeExpectation

interface InOrderOnlyMatcher<E, SC> {
    fun AssertionContainer<List<E>>.elementAssertionCreator(maybeElement: Option<E>, searchCriterion: SC): Assertion

    fun AssertionContainer<List<E>>.addSingleEntryAssertion(
        currentIndex: Int,
        searchCriterion: SC,
        translatableIndex: DescriptionIterableLikeExpectation
    ) {
        val maybeElement = maybeSubject.flatMap { list ->
            if (currentIndex < list.size) Some(list[currentIndex]) else None
        }
        val elementAssertion = elementAssertionCreator(maybeElement, searchCriterion)
        val assertion = maybeElement.map { elementAssertion }.getOrElse {
            //TODO 1.3.0: extract common pattern
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

        //TODO 1.3.0 replace with Representable and remove suppression
        @Suppress("DEPRECATION")
        append(
            assertionBuilder.feature
                .withDescriptionAndRepresentation(
                    ch.tutteli.atrium.reporting.translating.TranslatableWithArgs(translatableIndex, currentIndex),
                    maybeElement.getOrElse { DescriptionIterableLikeExpectation.SIZE_EXCEEDED }
                )
                .withAssertion(assertion)
                .build()
        )
    }

}
