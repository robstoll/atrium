package ch.tutteli.atrium.domain.creating.changers.impl.subjectchanger

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.domain.creating.changers.utils.propertiesOfThrowable
import ch.tutteli.atrium.domain.creating.collectors.collectAssertions

class ThrowableThrownFailureHandler<T : Throwable?, R>(
    private val maxStackTrace: Int
) : SubjectChanger.FailureHandler<T, R> {

    override fun createAssertion(
        originalAssertionContainer: Expect<T>,
        descriptiveAssertion: Assertion,
        maybeAssertionCreator: Option<Expect<R>.() -> Unit>
    ): Assertion {
        val assertions = mutableListOf(descriptiveAssertion)
        maybeAssertionCreator.fold({ /* nothing to do */ }) { assertionCreator ->
            assertions.add(
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .collectAssertions(None, assertionCreator)
                    .build()
            )
        }
        originalAssertionContainer.maybeSubject.fold(
            { /* nothing to do */ },
            {
                if (it != null) assertions.add(propertiesOfThrowable(it, maxStackTrace))
            }
        )
        return assertionBuilder.invisibleGroup
            .withAssertions(assertions.toList())
            .build()
    }
}

