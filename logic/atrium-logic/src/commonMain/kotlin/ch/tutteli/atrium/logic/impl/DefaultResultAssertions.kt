package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.*
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.creating.transformers.impl.ThrowableThrownFailureHandler
import ch.tutteli.atrium.translations.DescriptionResultExpectation.*
import kotlin.reflect.KClass

class DefaultResultAssertions : ResultAssertions {
    override fun <E, T : Result<E>> isSuccess(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<T, E> =
        container.extractFeature
            .withDescription(VALUE)
            .withRepresentationForFailure(IS_NOT_SUCCESS)
            .withFeatureExtraction {
                Option.someIf(it.isSuccess) { it.getOrThrow() }
            }
            .withoutOptions()
            .build()

    @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    override fun <TExpected : Throwable> isFailureOfType(
        container: AssertionContainer<Result<*>>,
        expectedType: KClass<TExpected>
    ): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> =
        container.manualFeature(EXCEPTION) {
            // fix is here for bug in kotlin 1.3, 1.4, 1.5 (fixed in 1.6) => https://youtrack.jetbrains.com/issue/KT-509747
            // due to the unwrap bug KT-50974, in case of a Failure, `exceptionOrNull` ...
            val failure = exceptionOrNull()
            val success = getOrNull()
            if (failure == null && // ... returns null, even though ...
                container.maybeSubject
                    .map { it.exceptionOrNull() }
                    .getOrElse { null } != null && // ... the container does not ...
                success is Result<*> // ... and for whatever reason Kotlin instead wraps the Failure into another Success ...
            ) {
                //... thus we need to unwrap the success again
                success.exceptionOrNull()
            } else {
                failure
            }
        }.transform().let { previousExpect ->
            FeatureExpect(
                previousExpect,
                FeatureExpectOptions(representationInsteadOfFeature = { it ?: IS_NOT_FAILURE })
            )
        }.toAssertionContainer().changeSubject.reportBuilder()
            .downCastTo(expectedType)
            .withFailureHandler(ThrowableThrownFailureHandler())
            .build()
}
