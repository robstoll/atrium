package ch.tutteli.atrium.domain.builders.creating.changers.impl.subjectchanger

import ch.tutteli.atrium.core.None
import ch.tutteli.atrium.core.Option
import ch.tutteli.atrium.core.Some
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.changers.SubjectChanger
import ch.tutteli.atrium.domain.creating.changers.subjectChanger
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

class DescriptionOptionImpl<T>(
    override val originalAssertionContainer: Expect<T>
) : SubjectChangerBuilder.DescriptionOption<T> {

    override fun withDescriptionAndRepresentation(
        description: Translatable,
        representation: Any?
    ): SubjectChangerBuilder.CheckOption<T> = SubjectChangerBuilder.CheckOption.create(
        originalAssertionContainer,
        description,
        representation ?: RawString.NULL
    )
}

class CheckOptionImpl<T>(
    override val originalAssertionContainer: Expect<T>,
    override val description: Translatable,
    override val representation: Any
) : SubjectChangerBuilder.CheckOption<T> {

    override fun withCheck(canBeTransformed: (T) -> Boolean): SubjectChangerBuilder.TransformationOption<T> =
        SubjectChangerBuilder.TransformationOption.create(this, canBeTransformed)
}

class TransformationOptionImpl<T>(
    override val checkOption: SubjectChangerBuilder.CheckOption<T>,
    override val canBeTransformed: (T) -> Boolean
) : SubjectChangerBuilder.TransformationOption<T> {

    override fun <R> withTransformation(transformation: (T) -> R): SubjectChangerBuilder.FailureHandlerOption<T, R> =
        SubjectChangerBuilder.FailureHandlerOption.create(checkOption, canBeTransformed, transformation)
}

class FailureHandlerOptionImpl<T, R>(
    override val checkOption: SubjectChangerBuilder.CheckOption<T>,
    override val canBeTransformed: (T) -> Boolean,
    override val transformation: (T) -> R
) : SubjectChangerBuilder.FailureHandlerOption<T, R> {

    override fun withFailureHandler(
        failureHandler: SubjectChanger.FailureHandler<T, R>
    ): SubjectChangerBuilder.FinalStep<T, R> =
        SubjectChangerBuilder.FinalStep.create(checkOption, canBeTransformed, transformation, failureHandler)

    override fun withDefaultFailureHandler(): SubjectChangerBuilder.FinalStep<T, R> =
        withFailureHandler(DefaultFailureHandlerImpl())
}

class FinalStepImpl<T, R>(
    override val checkOption: SubjectChangerBuilder.CheckOption<T>,
    override val canBeTransformed: (T) -> Boolean,
    override val transformation: (T) -> R,
    override val failureHandler: SubjectChanger.FailureHandler<T, R>
) : SubjectChangerBuilder.FinalStep<T, R> {

    override fun build(): ChangedSubjectPostStep<T, R> = ChangedSubjectPostStep(checkOption.originalAssertionContainer,
        transform = { transformIt(this, None) },
        transformAndApply = { assertionCreator -> transformIt(this, Some(assertionCreator)) }
    )

    private fun transformIt(expect: Expect<T>, subAssertions: Option<Expect<R>.() -> Unit>) =
        subjectChanger.reported(
            expect,
            checkOption.description,
            checkOption.representation,
            canBeTransformed,
            transformation,
            failureHandler,
            subAssertions
        )
}
