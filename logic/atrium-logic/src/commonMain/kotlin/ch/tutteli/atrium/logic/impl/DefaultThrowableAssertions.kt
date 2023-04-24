package ch.tutteli.atrium.logic.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.FeatureExpect
import ch.tutteli.atrium.creating.FeatureExpectOptions
import ch.tutteli.atrium.logic.ThrowableAssertions
import ch.tutteli.atrium.logic.changeSubject
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.creating.transformers.impl.ThrowableThrownFailureHandler
import ch.tutteli.atrium.logic.manualFeature
import ch.tutteli.atrium.logic.toAssertionContainer
import ch.tutteli.atrium.translations.DescriptionThrowableExpectation
import kotlin.reflect.KClass

class DefaultThrowableAssertions : ThrowableAssertions {

    @Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
    @UseExperimental(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    override fun <TExpected : Throwable> causeIsA(
        container: AssertionContainer<out Throwable>,
        expectedType: KClass<TExpected>
    ):  SubjectChangerBuilder.ExecutionStep<Throwable?, TExpected> =
        container.manualFeature(DescriptionThrowableExpectation.CAUSE) { cause }.transform().let { previousExpect ->
            //TODO 1.1.0 factor out a pattern, we are doing this more than once, in API we have withOptions
            FeatureExpect(
                previousExpect,
                FeatureExpectOptions(representationInsteadOfFeature = {
                    it ?: DescriptionThrowableExpectation.HAS_NO_CAUSE
                })
            ).toAssertionContainer().changeSubject.reportBuilder()
                .downCastTo(expectedType)
                .withFailureHandler(ThrowableThrownFailureHandler())
                .build()
        }
}
