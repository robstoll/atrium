//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.AssertionGroupDescriptionAndRepresentationOptionImpl
import ch.tutteli.atrium.reporting.LazyRepresentation
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Option step which allows to specify [AssertionGroup.description] and [AssertionGroup.representation].
 */
interface AssertionGroupDescriptionAndRepresentationOption<out T : AssertionGroupType, R> {
    /**
     * The previously defined [AssertionGroup.type].
     */
    val groupType: T

    /**
     * Turns the given [description] into an [Untranslatable] and the given [representationProvider] into a
     * [LazyRepresentation] and uses them as [AssertionGroup.description], [AssertionGroup.representation] respectively.
     */
    fun withDescriptionAndRepresentation(description: String, representationProvider: () -> Any?): R =
        withDescriptionAndRepresentation(Untranslatable(description), representationProvider)

    /**
     * Uses the given [description] as [AssertionGroup.description] and [representationProvider] to create a
     * [LazyRepresentation] which is used as [AssertionGroup.representation].
     */
    fun withDescriptionAndRepresentation(description: Translatable, representationProvider: () -> Any?): R =
        withDescriptionAndRepresentation(description, LazyRepresentation(representationProvider))


    /**
     * Wraps the given [description] into an [Untranslatable] and uses it as [AssertionGroup.description]
     * and uses [Text.EMPTY] as [AssertionGroup.representation].
     */
    fun withDescriptionAndEmptyRepresentation(description: String): R =
        withDescriptionAndRepresentation(Untranslatable(description), Text.EMPTY)

    /**
     * Uses the given [description] as [AssertionGroup.description] and [Text.EMPTY] as
     * [AssertionGroup.representation].
     */
    fun withDescriptionAndEmptyRepresentation(description: Translatable): R =
        withDescriptionAndRepresentation(description, Text.EMPTY)

    /**
     * Wraps the given [description] into an [Untranslatable] and delegates to the overload
     * which expects [Translatable].
     *
     * See the corresponding overload for more information.
     */
    fun withDescriptionAndRepresentation(description: String, representation: Any?): R =
        withDescriptionAndRepresentation(Untranslatable(description), representation)

    /**
     * Uses the given [description] as [AssertionGroup.description] and [representation]
     * as [AssertionGroup.representation] unless [representation] is null in which case a representation for
     * null is used (e.g. [Text.NULL]).
     *
     * Use the overload which expects a representation provider in case the computation is expensive.
     *
     * Notice, if you want to use text (a [String] which is treated as raw string in reporting) as representation,
     * then wrap it into a [Text] and pass it instead.
     */
    fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): R


    companion object {
        /**
         * Factory method to create an [AssertionGroupDescriptionAndRepresentationOption] with the
         * given expectation-group [type] and another [factory] method which creates the next step in the
         * building process.
         */
        fun <T : AssertionGroupType, R> create(
            type: T,
            factory: (T, Translatable, Any) -> R
        ): AssertionGroupDescriptionAndRepresentationOption<T, R> =
            AssertionGroupDescriptionAndRepresentationOptionImpl(type, factory)
    }
}

