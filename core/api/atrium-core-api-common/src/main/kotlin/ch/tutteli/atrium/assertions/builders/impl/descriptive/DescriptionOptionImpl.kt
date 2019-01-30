package ch.tutteli.atrium.assertions.builders.impl.descriptive

import ch.tutteli.atrium.assertions.builders.Descriptive
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

internal class DescriptionOptionImpl<R>(
    override val test: () -> Boolean,
    private val factory: (()-> Boolean, Translatable, Any) -> R
) : Descriptive.DescriptionOption<R> {

    override fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): R
        = factory(test, description, representation ?: RawString.NULL)
}
