package ch.tutteli.atrium.logic.kotlin_1_3.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.ExtractedFeaturePostStep
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.logic.impl.creating.changers.ThrowableThrownFailureHandler
import ch.tutteli.atrium.logic.kotlin_1_3.ResultAssertions
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.logic.toAssertionContainer
import ch.tutteli.atrium.translations.DescriptionResultAssertion.*
import kotlin.reflect.KClass

class DefaultResultAssertions : ResultAssertions {
    override fun <E, T : Result<E>> isSuccess(container: AssertionContainer<T>): ExtractedFeaturePostStep<T, E> =
        container.extractFeature
            .withDescription(VALUE)
            .withRepresentationForFailure(IS_NOT_SUCCESS)
            .withFeatureExtraction {
                Option.someIf(it.isSuccess) { it.getOrThrow() }
            }
            .withoutOptions()
            .build()

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class)
    override fun <TExpected : Throwable> isFailure(
        container: AssertionContainer<out Result<*>>,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<Throwable?, TExpected> =
        container.manualFeature(EXCEPTION) { exceptionOrNull() }.getExpectOfFeature().let { previousExpect ->
            FeatureExpect(
                previousExpect,
                FeatureExpectOptions(representationInsteadOfFeature = { it ?: IS_NOT_FAILURE })
            )
        }.toAssertionContainer().changeSubject.reportBuilder()
            .downCastTo(expectedType)
            .withFailureHandler(ThrowableThrownFailureHandler())
            .build()
}
