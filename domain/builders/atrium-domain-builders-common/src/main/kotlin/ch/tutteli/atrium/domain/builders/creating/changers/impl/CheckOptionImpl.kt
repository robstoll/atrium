package ch.tutteli.atrium.domain.builders.creating.changers.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

class CheckOptionImpl<T>(
    override val originalAssertionContainer: Expect<T>,
    override val description: Translatable,
    override val representation: Any
) : SubjectChangerBuilder.CheckOption<T> {

    override fun withCheck(canBeTransformed: (T) -> Boolean): SubjectChangerBuilder.TransformationOption<T> =
        SubjectChangerBuilder.TransformationOption.create(this, canBeTransformed)
}
