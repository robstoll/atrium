package ch.tutteli.atrium.domain.builders.creating.changers.impl

import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder

class SubjectProviderOptionImpl<T>(
    override val checkOption: SubjectChangerBuilder.CheckOption<T>,
    override val canBeTransformed: (T) -> Boolean
) : SubjectChangerBuilder.SubjectProviderOption<T> {

    override fun <R> withSubjectProvider(subjectProvider: () -> R): SubjectChangerBuilder.SubAssertionOption<T, R> =
        SubAssertionOptionImpl(checkOption, canBeTransformed, subjectProvider)
}
