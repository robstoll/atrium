package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.DescriptionAndEmptyRepresentationOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.RawString

interface DescriptionAndEmptyRepresentationOption<out T: AssertionGroupType, R> {
    /**
     * The [AssertionGroupType] which shall be used for the [AssertionGroup].
     */
    val groupType: T

    /**
     * Uses the given [description] as [AssertionGroup.name] and [RawString.EMPTY] as [AssertionGroup.representation].
     */
    fun withDescription(description: Translatable): R

    companion object {
        fun <T: AssertionGroupType, R> create(
            type: T,
            factory: (T, Translatable, Any) -> R
        ): DescriptionAndEmptyRepresentationOption<T, R> = DescriptionAndEmptyRepresentationOptionImpl(type, factory)
    }
}
