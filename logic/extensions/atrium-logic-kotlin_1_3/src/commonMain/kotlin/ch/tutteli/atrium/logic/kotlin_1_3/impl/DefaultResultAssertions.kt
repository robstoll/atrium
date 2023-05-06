package ch.tutteli.atrium.logic.kotlin_1_3.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.core.polyfills.cast
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.creating.FeatureExpectOptions
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

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    override fun <TExpected : Throwable> isFailureOfType(
        container: AssertionContainer<out Result<*>>,
        expectedType: KClass<TExpected>
    ): SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> =
        container.manualFeature(EXCEPTION) {


            //fix is here for bug in kotlin 1.3, 1.4, 1.5 (fixed in 1.6) => https://youtrack.jetbrains.com/issue/KT-50974
            //Comments below explain the fix
            val badResult = exceptionOrNull()
            //mapping manually to get internal value throwable - exception or null just produced null + we lost the exception
            val maybeSubjectResult = container.maybeSubject.map {
                onSuccess { null }
                onFailure { it.cause }
            }


            if (maybeSubjectResult.getOrElse { null } != null && badResult == null) {
                //there is something inside first option type so unwrap
                val successError = maybeSubjectResult.getOrElse { null }

                if (successError == null) {

                    return@manualFeature exceptionOrNull()
                }
                //if the result is a success we have another value to check
                if (successError.isSuccess) {

                    val valueInsideSuccess = getOrNull() as Result<*>
                    if (valueInsideSuccess.isFailure) {
                        return@manualFeature valueInsideSuccess.exceptionOrNull()
                    }
                }

            }



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
