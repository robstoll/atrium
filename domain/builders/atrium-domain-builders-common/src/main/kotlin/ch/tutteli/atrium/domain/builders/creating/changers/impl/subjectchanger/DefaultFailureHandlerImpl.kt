package ch.tutteli.atrium.domain.builders.creating.changers.impl.subjectchanger

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.builders.creating.collectors.collectAssertions
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger

class DefaultFailureHandlerImpl<T, R> : SubjectChanger.FailureHandler<T, R> {

    override fun createAssertion(
        originalAssertionContainer: Expect<T>,
        descriptiveAssertion: Assertion,
        assertionCreator: (Expect<R>.() -> Unit)?
    ): Assertion = if (assertionCreator != null) {
        AssertImpl.builder.invisibleGroup
            .withAssertions(
                descriptiveAssertion,
                AssertImpl.builder.explanatoryGroup
                    .withDefaultType
                    .collectAssertions(None, assertionCreator)
                    .build()
            )
            .build()
    } else {
        descriptiveAssertion
    }
}
