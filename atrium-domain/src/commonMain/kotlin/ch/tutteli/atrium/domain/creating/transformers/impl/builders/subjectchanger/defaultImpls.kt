package ch.tutteli.atrium.domain.creating.transformers.impl.builders.subjectchanger

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.domain.creating.transformers.SubjectChanger
import ch.tutteli.atrium.domain.creating.transformers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.creating.transformers.impl.BaseTransformationExecutionStep
import ch.tutteli.atrium.domain.creating.transformers.subjectChanger
import ch.tutteli.atrium.reporting.reportables.InlineElement
import ch.tutteli.atrium.reporting.Text

class KindStepImpl<SubjectT>(
    override val container: ProofContainer<SubjectT>
) : SubjectChangerBuilder.KindStep<SubjectT> {

    override fun reportBuilder(): SubjectChangerBuilder.DescriptionRepresentationStep<SubjectT> =
        SubjectChangerBuilder.DescriptionRepresentationStep(container)
}

class DescriptionRepresentationStepImpl<SubjectT>(
    override val container: ProofContainer<SubjectT>
) : SubjectChangerBuilder.DescriptionRepresentationStep<SubjectT> {

    override fun withDescriptionAndRepresentation(
        description: InlineElement,
        representation: Any?
    ): SubjectChangerBuilder.TransformationStep<SubjectT> = SubjectChangerBuilder.TransformationStep(
        container,
        description,
        representation ?: Text.NULL
    )
}

class TransformationStepImpl<SubjectT>(
    override val container: ProofContainer<SubjectT>,
    override val description: InlineElement,
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
            actionAndApply = { container, expectationCreatorWithUsageHints ->
                transformIt(
                    container,
                    Some(expectationCreatorWithUsageHints)
                )
            }
        )

    private fun transformIt(
        container: ProofContainer<SubjectT>,
        maybeSubExpectationCreatorWithUsageHints: Option<ExpectationCreatorWithUsageHints<SubjectAfterChangeT>>
    ) = container.subjectChanger.reported(
        container,
        transformationStep.description,
        transformationStep.representation,
        transformation,
        failureHandler,
        maybeSubExpectationCreatorWithUsageHints
    )
}

class ExecutionStepImpl<SubjectT, SubjectAfterChangeT>(
    container: ProofContainer<SubjectT>,
    action: ProofContainer<SubjectT>.() -> Expect<SubjectAfterChangeT>,
    actionAndApply: ProofContainer<SubjectT>.(ExpectationCreatorWithUsageHints<SubjectAfterChangeT>) -> Expect<SubjectAfterChangeT>
) : SubjectChangerBuilder.ExecutionStep<SubjectT, SubjectAfterChangeT>,
    BaseTransformationExecutionStep<SubjectT, SubjectAfterChangeT, Expect<SubjectAfterChangeT>>(
        container,
        action,
        actionAndApply
    )
