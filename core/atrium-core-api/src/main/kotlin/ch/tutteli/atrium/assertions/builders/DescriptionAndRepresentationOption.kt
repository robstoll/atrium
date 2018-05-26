package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.DescriptionAndRepresentationOptionImpl
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.translating.Translatable

interface DescriptionAndRepresentationOption<out T : AssertionGroupType, R> {
    /**
     * The [AssertionGroupType] which shall be used for the [AssertionGroup].
     */
    val groupType: T

    /**
     * Uses the given [description] as [AssertionGroup.name] and [representationProvider] to create a
     * [LazyRepresentation] which is used as [AssertionGroup.representation].
     */
    fun withDescriptionAndRepresentation(description: Translatable, representationProvider: () -> Any): R
        = withDescriptionAndRepresentation(description, LazyRepresentation(representationProvider))

    /**
     * Uses the given [description] as [AssertionGroup.name] and [representation] as [AssertionGroup.representation].
     */
    fun withDescriptionAndRepresentation(description: Translatable, representation: Any): R

    companion object {
        fun <T: AssertionGroupType, R> create(
            type: T,
            factory: (T, Translatable, Any) -> R
        ): DescriptionAndRepresentationOption<T, R> = DescriptionAndRepresentationOptionImpl(type, factory)
    }
}

