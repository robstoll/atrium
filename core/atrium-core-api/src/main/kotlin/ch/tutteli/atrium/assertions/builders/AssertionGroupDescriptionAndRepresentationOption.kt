package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.AssertionGroupDescriptionAndRepresentationOptionImpl
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Option step which allows to specify [AssertionGroup.name] and [AssertionGroup.representation].
 */
interface AssertionGroupDescriptionAndRepresentationOption<out T : AssertionGroupType, R> {
    /**
     * The previously defined [AssertionGroup.type].
     */
    val groupType: T

    /**
     * Uses the given [description] as [AssertionGroup.name] and [representationProvider] to create a
     * [LazyRepresentation] which is used as [AssertionGroup.representation].
     */
    fun withDescriptionAndRepresentation(description: Translatable, representationProvider: () -> Any): R
        = withDescriptionAndRepresentation(description, LazyRepresentation(representationProvider))

    /**
     * Uses the given [description] as [AssertionGroup.name] and [RawString.EMPTY] as [AssertionGroup.representation].
     */
    fun withDescriptionAndEmptyRepresentation(description: Translatable): R
        = withDescriptionAndRepresentation(description, RawString.EMPTY)

    /**
     * Uses the given [description] as [AssertionGroup.name] and [representation] as [AssertionGroup.representation]
     * unless [representation] is null in which case [RawString.NULL] is used.
     */
    fun withDescriptionAndNullableRepresentation(description: Translatable, representation: Any?): R
        = withDescriptionAndRepresentation(description, representation ?: RawString.NULL)

    /**
     * Uses the given [description] as [AssertionGroup.name] and [representation] as [AssertionGroup.representation].
     */
    fun withDescriptionAndRepresentation(description: Translatable, representation: Any): R


    companion object {
        fun <T: AssertionGroupType, R> create(
            type: T,
            factory: (T, Translatable, Any) -> R
        ): AssertionGroupDescriptionAndRepresentationOption<T, R> = AssertionGroupDescriptionAndRepresentationOptionImpl(type, factory)
    }
}

