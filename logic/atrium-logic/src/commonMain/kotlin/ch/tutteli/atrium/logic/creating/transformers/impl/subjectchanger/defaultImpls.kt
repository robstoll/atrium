package ch.tutteli.atrium.logic.creating.transformers.impl.subjectchanger

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.logic.creating.transformers.SubjectChanger
import ch.tutteli.atrium.logic.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.logic.creating.transformers.impl.BaseTransformationExecutionStep
import ch.tutteli.atrium.logic.creating.transformers.subjectChanger
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable

class KindStepImpl<T>(
    override val container: AssertionContainer<T>
) : SubjectChangerBuilder.KindStep<T> {

    override fun reportBuilder(): SubjectChangerBuilder.DescriptionRepresentationStep<T> =
        SubjectChangerBuilder.DescriptionRepresentationStep(container)
}

class DescriptionRepresentationStepImpl<T>(
    override val container: AssertionContainer<T>
) : SubjectChangerBuilder.DescriptionRepresentationStep<T> {

    override fun withDescriptionAndRepresentation(
        description: Translatable,
        representation: Any?
    ): SubjectChangerBuilder.TransformationStep<T> = SubjectChangerBuilder.TransformationStep(
        container,
        description,
        representation ?: Text.NULL
    )
}

class TransformationStepImpl<T>(
    override val container: AssertionContainer<T>,
    override val description: Translatable,
    override val representation: Any
) : SubjectChangerBuilder.TransformationStep<T> {

    override fun <R> withTransformation(
        transformation: (T) -> Option<R>
    ): SubjectChangerBuilder.FailureHandlerStep<T, R> =
        SubjectChangerBuilder.FailureHandlerStep(this, transformation)
}

class FailureHandlerStepImpl<T, R>(
    override val transformationStep: SubjectChangerBuilder.TransformationStep<T>,
    override val transformation: (T) -> Option<R>
) : SubjectChangerBuilder.FailureHandlerStep<T, R> {

    override fun withFailureHandler(
        failureHandler: SubjectChanger.FailureHandler<T, R>
    ): SubjectChangerBuilder.FinalStep<T, R> =
        SubjectChangerBuilder.FinalStep(transformationStep, transformation, failureHandler)

    override fun withDefaultFailureHandler(): SubjectChangerBuilder.FinalStep<T, R> =
        withFailureHandler(DefaultFailureHandlerImpl())
}

class FinalStepImpl<T, R>(
    override val transformationStep: SubjectChangerBuilder.TransformationStep<T>,
    override val transformation: (T) -> Option<R>,
    override val failureHandler: SubjectChanger.FailureHandler<T, R>
) : SubjectChangerBuilder.FinalStep<T, R> {

    override fun build(): SubjectChangerBuilder.ExecutionStep<T, R> =
        SubjectChangerBuilder.ExecutionStep(
            transformationStep.container,
            action = { container -> transformIt(container, None) },
            actionAndApply = { container, assertionCreator -> transformIt(container, Some(assertionCreator)) }
        )

    private fun transformIt(container: AssertionContainer<T>, maybeSubAssertions: Option<Expect<R>.() -> Unit>) =
        container.subjectChanger.reported(
            container,
            transformationStep.description,
            transformationStep.representation,
            transformation,
            failureHandler,
            maybeSubAssertions
        )
}

class ExecutionStepImpl<T, R>(
    container: AssertionContainer<T>,
    action: AssertionContainer<T>.() -> Expect<R>,
    actionAndApply: AssertionContainer<T>.(Expect<R>.() -> Unit) -> Expect<R>
) : SubjectChangerBuilder.ExecutionStep<T, R>,
    BaseTransformationExecutionStep<T, R, Expect<R>>(container, action, actionAndApply)
