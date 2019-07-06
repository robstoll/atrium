package ch.tutteli.atrium.domain.builders.creating.changers.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder

class SubAssertionOptionImpl<T, R>(
    override val checkOption: SubjectChangerBuilder.CheckOption<T>,
    override val canBeTransformed: (T) -> Boolean,
    override val transformation: (T) -> R
) : SubjectChangerBuilder.SubAssertionOption<T, R> {

    override fun withoutSubAssertions(): SubjectChangerBuilder.FinalStep<T, R> =
        FinalStepImpl(checkOption, canBeTransformed, transformation, null)

    override fun withSubAssertions(assertionCreator: Expect<R>.() -> Unit): SubjectChangerBuilder.FinalStep<T, R> =
        FinalStepImpl(checkOption, canBeTransformed, transformation, assertionCreator)
}
