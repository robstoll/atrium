package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.builders.DescriptiveLikeAssertionDescriptionOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class DescriptiveLikeAssertionDescriptionOptionImpl<R>(
    override val test: () -> Boolean,
    private val factory: (()-> Boolean, Translatable, Any) -> R
) : DescriptiveLikeAssertionDescriptionOption<R> {

    override fun withDescriptionAndRepresentation(description: Translatable, representation: Any): R
        = factory(test, description, representation)
}
