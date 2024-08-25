package ch.tutteli.atrium.logic.creating.transformers.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.invisibleGroup
import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.logic.creating.collectors.collectAssertions
import ch.tutteli.atrium.logic.creating.transformers.SubjectChanger
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation.OCCURRED_EXCEPTION_PROPERTIES

class ThrowableThrownFailureHandler<SubjectT : Throwable?, SubjectAfterChangeT> :
    SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT> {

    override fun createAssertion(
        container: AssertionContainer<SubjectT>,
        descriptiveAssertion: Assertion,
        maybeAssertionCreator: Option<Expect<SubjectAfterChangeT>.() -> Unit>
    ): Assertion {
        val assertions = mutableListOf(descriptiveAssertion)
        maybeAssertionCreator.fold({ /* nothing to do */ }) { assertionCreator ->
            assertions.add(
                assertionBuilder.explanatoryGroup
                    .withDefaultType
                    .collectAssertions(container, None, assertionCreator)
                    .build()
            )
        }
        container.maybeSubject.fold(
            { /* nothing to do */ },
            {
                if (it != null) assertions.add(
                    ch.tutteli.atrium.logic.creating.transformers.propertiesOfThrowable(it, container)
                )
            }
        )

        return if (assertions.size == 1) {
            assertions[0]
        } else {
            assertionBuilder.invisibleGroup
                .withAssertions(assertions)
                .build()
        }
    }

    companion object {

        /**
         * Returns an [AssertionGroup] with an [ExplanatoryAssertionGroupType] containing properties
         * of the given [throwable].
         */
        @OptIn(ExperimentalComponentFactoryContainer::class)
        @Deprecated(
            "use the function in package  ch.tutteli.atrium.logic.creating.transformers", ReplaceWith(
                "ch.tutteli.atrium.logic.creating.transformers.propertiesOfThrowable(throwable, container, explanation)",
            )
        )
        fun propertiesOfThrowable(
            throwable: Throwable,
            container: AssertionContainer<*>,
            explanation: Assertion = createExplanation(throwable)
        ): AssertionGroup =
            ch.tutteli.atrium.logic.creating.transformers.propertiesOfThrowable(throwable, container, explanation)


        private fun createExplanation(throwable: Throwable) =
            assertionBuilder.explanatory
                .withExplanation(
                    OCCURRED_EXCEPTION_PROPERTIES,
                    throwable::class.simpleName ?: throwable::class.fullName
                )
                .build()

    }
}

/**
 * Hook for platform specific additional hints.
 */
expect fun createAdditionalHints(throwable: Throwable): List<Assertion>
