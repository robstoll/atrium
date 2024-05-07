//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders.impl

import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionGroupDescriptionAndRepresentationOption
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable

internal class AssertionGroupDescriptionAndRepresentationOptionImpl<out T : AssertionGroupType, R>(
    override val groupType: T,
    private val factory: (T, Translatable, Any) -> R
) : AssertionGroupDescriptionAndRepresentationOption<T, R> {

    override fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): R =
        factory(groupType, description, representation ?: Text.NULL)
}
