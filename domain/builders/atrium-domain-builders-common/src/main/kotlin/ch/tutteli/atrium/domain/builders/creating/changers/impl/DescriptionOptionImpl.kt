package ch.tutteli.atrium.domain.builders.creating.changers.impl

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.creating.changers.SubjectChangerBuilder
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

class DescriptionOptionImpl<T>(
    override val originalAssertionContainer: Expect<T>
) : SubjectChangerBuilder.DescriptionOption<T> {

    override fun withDescriptionAndRepresentation(
        description: Translatable,
        representation: Any?
    ): SubjectChangerBuilder.CheckOption<T> =
        CheckOptionImpl(originalAssertionContainer, description, representation ?: RawString.NULL)
}
