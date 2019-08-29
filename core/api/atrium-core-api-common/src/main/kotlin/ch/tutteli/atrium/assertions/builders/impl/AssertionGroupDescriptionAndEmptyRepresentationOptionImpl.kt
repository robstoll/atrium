package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionGroupDescriptionAndEmptyRepresentationOption
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

internal class AssertionGroupDescriptionAndEmptyRepresentationOptionImpl<out T : AssertionGroupType, R>(
    override val groupType: T,
    private val factory: (T, Translatable, Any) -> R
) : AssertionGroupDescriptionAndEmptyRepresentationOption<T, R> {

    override fun withDescription(description: Translatable): R = factory(groupType, description, RawString.EMPTY)
}
