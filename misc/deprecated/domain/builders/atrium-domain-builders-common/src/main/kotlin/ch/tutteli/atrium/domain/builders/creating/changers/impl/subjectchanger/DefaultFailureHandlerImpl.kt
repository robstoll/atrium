package ch.tutteli.atrium.domain.builders.creating.changers.impl.subjectchanger

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
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
        ExpectImpl.builder.invisibleGroup
            .withAssertions(
                descriptiveAssertion,
                ExpectImpl.builder.explanatoryGroup
                    .withDefaultType
                    .collectAssertions(None, assertionCreator)
                    .build()
            )
            .build()
    }
}
