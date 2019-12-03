package ch.tutteli.atrium.domain.builders.creating.changers.impl.subjectchanger

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.domain.creating.changers.subjectChanger
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

class KindStepImpl<T>(
    override val originalAssertionContainer: Expect<T>
) : SubjectChangerBuilder.KindStep<T> {

    override fun reportBuilder(): SubjectChangerBuilder.DescriptionRepresentationStep<T> =
        SubjectChangerBuilder.DescriptionRepresentationStep.create(originalAssertionContainer)
}

@Suppress("DEPRECATION")
class DeprecatedKindStepImpl<T>(
    override val originalPlant: SubjectProvider<T>
) : SubjectChangerBuilder.DeprecatedKindStep<T>

class DescriptionRepresentationStepImpl<T>(
    override val originalAssertionContainer: Expect<T>
) : SubjectChangerBuilder.DescriptionRepresentationStep<T> {

    override fun withDescriptionAndRepresentation(
        description: Translatable,
        representation: Any?
    ): SubjectChangerBuilder.TransformationStep<T> = SubjectChangerBuilder.TransformationStep.create(
        originalAssertionContainer,
        description,
        representation ?: RawString.NULL
    )
}

class TransformationStepImpl<T>(
    override val originalAssertionContainer: Expect<T>,
    override val description: Translatable,
    override val representation: Any
) : SubjectChangerBuilder.TransformationStep<T> {

    override fun <R> withTransformation(
        transformation: (T) -> Option<R>
    ): SubjectChangerBuilder.FailureHandlerOption<T, R> =
        SubjectChangerBuilder.FailureHandlerOption.create(this, transformation)
}

class FailureHandlerOptionImpl<T, R>(
    override val transformationStep: SubjectChangerBuilder.TransformationStep<T>,
    override val transformation: (T) -> Option<R>
) : SubjectChangerBuilder.FailureHandlerOption<T, R> {

    override fun withFailureHandler(
        failureHandler: SubjectChanger.FailureHandler<T, R>
    ): SubjectChangerBuilder.FinalStep<T, R> =
        SubjectChangerBuilder.FinalStep.create(transformationStep, transformation, failureHandler)

    override fun withDefaultFailureHandler(): SubjectChangerBuilder.FinalStep<T, R> =
        withFailureHandler(DefaultFailureHandlerImpl())
}

class FinalStepImpl<T, R>(
    override val transformationStep: SubjectChangerBuilder.TransformationStep<T>,
    override val transformation: (T) -> Option<R>,
    override val failureHandler: SubjectChanger.FailureHandler<T, R>
) : SubjectChangerBuilder.FinalStep<T, R> {

    override fun build(): ChangedSubjectPostStep<T, R> =
        ChangedSubjectPostStep(transformationStep.originalAssertionContainer,
            transform = { transformIt(this, None) },
            transformAndApply = { assertionCreator -> transformIt(this, Some(assertionCreator)) }
        )

    private fun transformIt(expect: Expect<T>, subAssertions: Option<Expect<R>.() -> Unit>) =
        subjectChanger.reported(
            expect,
            transformationStep.description,
            transformationStep.representation,
            transformation,
            failureHandler,
            subAssertions
        )
}
