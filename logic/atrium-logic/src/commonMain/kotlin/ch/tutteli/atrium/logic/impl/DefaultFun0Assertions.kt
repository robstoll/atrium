package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.core.Either
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.Left
import ch.tutteli.atrium.core.Right
import ch.tutteli.atrium.creating.*
import ch.tutteli.atrium.logic.Fun0Assertions
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.creating.transformers.FeatureExtractorBuilder
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.creating.transformers.impl.ThrowableThrownFailureHandler
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.logic.toAssertionContainer
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import ch.tutteli.atrium.translations.DescriptionFunLikeExpectation.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.translations.DescriptionFunLikeExpectation.THROWN_EXCEPTION_WHEN_CALLED
import kotlin.reflect.KClass

class DefaultFun0Assertions : Fun0Assertions {

    override fun <TExpected : Throwable> toThrow(
        container: AssertionContainer<() -> Any?>,
        expectedType: KClass<TExpected>
    ): SubjectChangerBuilder.ExecutionStep<*, TExpected> {
        // we use manualFeature and not extractFeature since we never want to fail the feature extraction
        // because we want to show the planned downCast in the error message
        return container.manualFeature(THROWN_EXCEPTION_WHEN_CALLED) {
            catchAndAdjustThrowable(container, this)
                .fold({ it }, { /* use null as subject in case no exception occurred */ null })
        }.transform().let { previousExpect ->
            @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
            FeatureExpect(
                previousExpect,
                FeatureExpectOptions(representationInsteadOfFeature = {
                    it ?: NO_EXCEPTION_OCCURRED
                })
            ).toAssertionContainer().changeSubject.reportBuilder()
                .downCastTo(expectedType)
                .withFailureHandler(ThrowableThrownFailureHandler())
                .build()
        }
    }

    private inline fun <R> catchAndAdjustThrowable(
        container: AssertionContainer<*>,
        act: () -> R
    ): Either<Throwable, R> =
        try {
            Right(act())
        } catch (throwable: Throwable) {
            @OptIn(ExperimentalComponentFactoryContainer::class)
            container.components.build<AtriumErrorAdjuster>().adjust(throwable)
            Left(throwable)
        }

    override fun <R, T : () -> R> notToThrow(container: AssertionContainer<T>): FeatureExtractorBuilder.ExecutionStep<*, R> =
        container.manualFeature("invoke()") { this.invoke() }
}
