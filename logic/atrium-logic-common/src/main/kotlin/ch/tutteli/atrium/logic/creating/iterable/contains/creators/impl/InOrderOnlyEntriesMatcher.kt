package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.collectBasedOnSubject
import ch.tutteli.atrium.logic.toBeNullIfNullGivenElse

class InOrderOnlyEntriesMatcher<E : Any> : InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> {

    override fun AssertionContainer<List<E?>>.elementAssertionCreator(
        maybeElement: Option<E?>,
        searchCriterion: (Expect<E>.() -> Unit)?
    ): Assertion = collectBasedOnSubject(maybeElement) {
        _logicAppend { toBeNullIfNullGivenElse(searchCriterion) }
    }
}
