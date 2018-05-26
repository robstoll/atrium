package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.DescriptionAndEmptyRepresentationOption
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

internal class DescriptionAndEmptyRepresentationOptionImpl<out T: AssertionGroupType, R>(
    override val groupType: T,
    private val factory: (T, Translatable, Any) -> R
) : DescriptionAndEmptyRepresentationOption<T, R> {

    override fun withDescription(description: Translatable): R = factory(groupType, description, RawString.EMPTY)
}
