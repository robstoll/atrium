package ch.tutteli.atrium.domain.builders.creating.changers.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.domain.creating.changers.subjectChanger

class FinalStepImpl<T, R>(
    override val checkOption: SubjectChangerBuilder.CheckOption<T>,
    override val canBeTransformed: (T) -> Boolean,
    override val subjectProvider: () -> R,
    override val subAssertions: (Expect<R>.() -> Unit)?
) : SubjectChangerBuilder.FinalStep<T, R> {

    override fun build(): Expect<R> = subjectChanger.reported(
        checkOption.originalAssertionContainer,
        checkOption.description,
        checkOption.representation,
        canBeTransformed,
        subjectProvider,
        subAssertions
    )
}
