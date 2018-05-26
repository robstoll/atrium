package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.DescriptionAndRepresentationOption
import ch.tutteli.atrium.reporting.translating.Translatable

internal class DescriptionAndRepresentationOptionImpl<out T: AssertionGroupType, R>(
    override val groupType: T,
    private val factory: (T, Translatable, Any) -> R
) : DescriptionAndRepresentationOption<T, R> {

    override fun withDescriptionAndRepresentation(description: Translatable, representation: Any): R
        = factory(groupType, description, representation)
}
