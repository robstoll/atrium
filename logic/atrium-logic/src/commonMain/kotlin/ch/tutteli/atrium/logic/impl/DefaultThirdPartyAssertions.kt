package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.ThirdPartyAssertions
import ch.tutteli.atrium.logic.creating.transformers.propertiesOfThrowable

class DefaultThirdPartyAssertions : ThirdPartyAssertions {
    override fun <T> toHoldThirdPartyExpectation(
        container: AssertionContainer<T>,
        description: String,
        representation: Any?,
        expectationExecutor: (T) -> Unit
    ): Assertion = container.maybeSubject.fold(
        {
            // no subject here, we return a descriptive assertion so that we see it in an explanatory group
            assertionBuilder.descriptive
                .failing
                .withDescriptionAndRepresentation(description, representation)
                .build()
        },
        { subject ->
            try {
                expectationExecutor(subject)
                assertionBuilder.descriptive
                    .holding
                    .withDescriptionAndRepresentation(description, representation)
                    .build()
            } catch (e: Throwable) {
                assertionBuilder.fixedClaimGroup
                    .withListType
                    .failing
                    .withDescriptionAndRepresentation(description, representation)
                    .withAssertion(propertiesOfThrowable(e, container ))
                    .build()
            }
        }
    )
}
