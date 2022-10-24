package ch.tutteli.atrium.logic.kotlin_1_3.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.creating.transformers.impl.ThrowableThrownFailureHandler
import ch.tutteli.atrium.logic.extractFeature
import ch.tutteli.atrium.logic.kotlin_1_3.ResultAssertions
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.logic.toAssertionContainer
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
        container: AssertionContainer<out Result<*>>,
        expectedType: KClass<TExpected>
    ):  SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> {
        println("heeere and subject is: ${container.maybeSubject.getOrElse { "no subject?" }}")
        return container.manualFeature(EXCEPTION) {
            println("suddenly no longer a Failure?: $this")
            exceptionOrNull()
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
}
