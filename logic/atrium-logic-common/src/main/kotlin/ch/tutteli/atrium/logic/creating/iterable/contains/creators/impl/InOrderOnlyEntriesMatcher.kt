package ch.tutteli.atrium.logic.creating.iterable.contains.creators.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.collectors.assertionCollector
import ch.tutteli.atrium.logic._logicAppend
import ch.tutteli.atrium.logic.toBeNullIfNullGivenElse

class InOrderOnlyEntriesMatcher<E : Any> : InOrderOnlyMatcher<E?, (Expect<E>.() -> Unit)?> {

    override fun elementAssertionCreator(
        maybeElement: Option<E?>,
        searchCriterion: (Expect<E>.() -> Unit)?
    ): Assertion = assertionCollector.collect(maybeElement) {
        _logicAppend { toBeNullIfNullGivenElse(searchCriterion) }
    }
}
