//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.AssertionGroupDescriptionAndEmptyRepresentationOptionImpl
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Option step which allows to specify the [AssertionGroup.description] -- [Text.EMPTY] is
 * used as [AssertionGroup.representation].
 */
interface AssertionGroupDescriptionAndEmptyRepresentationOption<out T : AssertionGroupType, R> {
    /**
     * The previously defined [AssertionGroup.type].
     */
    val groupType: T


    /**
     * Wraps the given [description] into an [Untranslatable] and uses it as [AssertionGroup.description] and
     * uses [Text.EMPTY] as [AssertionGroup.representation].
     */
    fun withDescription(description: String): R = withDescription(Untranslatable(description))

    /**
     * Uses the given [description] as [AssertionGroup.description] and
     * [Text.EMPTY] as [AssertionGroup.representation].
     */
    fun withDescription(description: Translatable): R

    companion object {
        /**
         * Factory method to create a [AssertionGroupDescriptionAndEmptyRepresentationOption] step in the building
         * process of an [AssertionGroup].
         */
        fun <T : AssertionGroupType, R> create(
            type: T,
            factory: (T, Translatable, Any) -> R
        ): AssertionGroupDescriptionAndEmptyRepresentationOption<T, R> =
            AssertionGroupDescriptionAndEmptyRepresentationOptionImpl(type, factory)
    }
}
