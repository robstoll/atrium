//TODO remove with 1.0.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.creating.changers.impl.subjectchanger

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger

class DefaultFailureHandlerImpl<T, R> : SubjectChanger.FailureHandler<T, R> {

    override fun createAssertion(
        originalAssertionContainer: Expect<T>,
        descriptiveAssertion: Assertion,
        maybeAssertionCreator: Option<Expect<R>.() -> Unit>
    ): Assertion = maybeAssertionCreator.fold({
        descriptiveAssertion
    }) { assertionCreator ->
        assertionBuilder.invisibleGroup
            .withAssertions(
                descriptiveAssertion,
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .collectAssertions(None, assertionCreator)
                    .build()
            )
            .build()
    }
}
