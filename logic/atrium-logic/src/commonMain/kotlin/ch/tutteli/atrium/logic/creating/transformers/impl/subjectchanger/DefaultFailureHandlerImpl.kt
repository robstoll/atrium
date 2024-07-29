//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.transformers.impl.subjectchanger

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.collectors.collectAssertions
import ch.tutteli.atrium.logic.creating.transformers.SubjectChanger

class DefaultFailureHandlerImpl<SubjectT, SubjectAfterChangeT> : SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT> {

    override fun createAssertion(
        container: AssertionContainer<SubjectT>,
        descriptiveAssertion: Assertion,
        maybeAssertionCreator: Option<Expect<SubjectAfterChangeT>.() -> Unit>
    ): Assertion = maybeAssertionCreator.fold({
        descriptiveAssertion
    }) { assertionCreator ->
        assertionBuilder.invisibleGroup
            .withAssertions(
                descriptiveAssertion,
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .collectAssertions(container, None, assertionCreator)
                    .build()
            )
            .build()
    }
}
