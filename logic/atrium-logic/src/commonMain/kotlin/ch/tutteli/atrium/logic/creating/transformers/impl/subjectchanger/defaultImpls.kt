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

class KindStepImpl<SubjectT>(
    override val container: AssertionContainer<SubjectT>
) : SubjectChangerBuilder.KindStep<SubjectT> {

    override fun reportBuilder(): SubjectChangerBuilder.DescriptionRepresentationStep<SubjectT> =
        SubjectChangerBuilder.DescriptionRepresentationStep(container)
}

class DescriptionRepresentationStepImpl<SubjectT>(
    override val container: AssertionContainer<SubjectT>
) : SubjectChangerBuilder.DescriptionRepresentationStep<SubjectT> {


    override fun withDescriptionAndRepresentation(
        //TODO 1.3.0 remove suppress again, use InlineElement instead
        @Suppress("DEPRECATION")
        description: ch.tutteli.atrium.reporting.translating.Translatable,
        representation: Any?
    ): SubjectChangerBuilder.TransformationStep<SubjectT> = SubjectChangerBuilder.TransformationStep(
        container,
        description,
        representation ?: Text.NULL
    )
}

class TransformationStepImpl<SubjectT>(
    override val container: AssertionContainer<SubjectT>,
    //TODO 1.3.0 remove suppress again, use InlineElement instead
    @Suppress("DEPRECATION")
    override val description: ch.tutteli.atrium.reporting.translating.Translatable,
    override val representation: Any
) : SubjectChangerBuilder.TransformationStep<SubjectT> {

    override fun <SubjectAfterChangeT> withTransformation(
        transformation: (SubjectT) -> Option<SubjectAfterChangeT>
    ): SubjectChangerBuilder.FailureHandlerStep<SubjectT, SubjectAfterChangeT> =
        SubjectChangerBuilder.FailureHandlerStep(this, transformation)
}

class FailureHandlerStepImpl<SubjectT, SubjectAfterChangeT>(
    override val transformationStep: SubjectChangerBuilder.TransformationStep<SubjectT>,
    override val transformation: (SubjectT) -> Option<SubjectAfterChangeT>
) : SubjectChangerBuilder.FailureHandlerStep<SubjectT, SubjectAfterChangeT> {

    override fun withFailureHandler(
        failureHandler: SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT>
    ): SubjectChangerBuilder.FinalStep<SubjectT, SubjectAfterChangeT> =
        SubjectChangerBuilder.FinalStep(transformationStep, transformation, failureHandler)

    override fun withDefaultFailureHandler(): SubjectChangerBuilder.FinalStep<SubjectT, SubjectAfterChangeT> =
        withFailureHandler(DefaultFailureHandlerImpl())
}

class FinalStepImpl<SubjectT, SubjectAfterChangeT>(
    override val transformationStep: SubjectChangerBuilder.TransformationStep<SubjectT>,
    override val transformation: (SubjectT) -> Option<SubjectAfterChangeT>,
    override val failureHandler: SubjectChanger.FailureHandler<SubjectT, SubjectAfterChangeT>
) : SubjectChangerBuilder.FinalStep<SubjectT, SubjectAfterChangeT> {

    override fun build(): SubjectChangerBuilder.ExecutionStep<SubjectT, SubjectAfterChangeT> =
        SubjectChangerBuilder.ExecutionStep(
            transformationStep.container,
            action = { container -> transformIt(container, None) },
            actionAndApply = { container, assertionCreator -> transformIt(container, Some(assertionCreator)) }
        )

    private fun transformIt(container: AssertionContainer<SubjectT>, maybeSubAssertions: Option<Expect<SubjectAfterChangeT>.() -> Unit>) =
        container.subjectChanger.reported(
            container,
            transformationStep.description,
            transformationStep.representation,
            transformation,
            failureHandler,
            maybeSubAssertions
        )
}

class ExecutionStepImpl<SubjectT, SubjectAfterChangeT>(
    container: AssertionContainer<SubjectT>,
    action: AssertionContainer<SubjectT>.() -> Expect<SubjectAfterChangeT>,
    actionAndApply: AssertionContainer<SubjectT>.(Expect<SubjectAfterChangeT>.() -> Unit) -> Expect<SubjectAfterChangeT>
) : SubjectChangerBuilder.ExecutionStep<SubjectT, SubjectAfterChangeT>,
    BaseTransformationExecutionStep<SubjectT, SubjectAfterChangeT, Expect<SubjectAfterChangeT>>(container, action, actionAndApply)
