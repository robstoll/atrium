package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.toBeNull

class InOrderOnlyEntriesMatcher<E : Any> : InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> {

    override fun elementAssertionCreator(
        maybeElement: Option<E?>,
        searchCriterion: (Expect<E>.() -> Unit)?
    ): Assertion = assertionCollector.collect(maybeElement) {
        _logicAppend { silentToBeNullIfNullGivenElse(searchCriterion) }
    }

    //TODO 0.16.0 same as in DefaultMapLikeContainsAssertions
    private fun AssertionContainer<E?>.silentToBeNullIfNullGivenElse(assertionCreatorOrNull: (Expect<E>.() -> Unit)?): Assertion =
        if (assertionCreatorOrNull == null) {
            toBeNull()
        } else {
            val assertion = assertionCollector.collect(maybeSubject.flatMap { if (it != null) Some(it) else None }) {
                addAssertionsCreatedBy(assertionCreatorOrNull)
            }
            maybeSubject.fold(
                {
                    // already in an explanatory assertion context, no need to wrap it again
                    assertion
                },
                {
                    if (it != null) {
                        assertion
                    } else {
                        assertionBuilder.explanatoryGroup
                            .withDefaultType
                            .withAssertion(assertion)
                            .failing
                            .build()
                    }
                }
            )
        }
}
