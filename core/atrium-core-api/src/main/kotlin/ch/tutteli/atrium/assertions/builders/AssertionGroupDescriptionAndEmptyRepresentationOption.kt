package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.AssertionGroupDescriptionAndEmptyRepresentationOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.RawString

/**
 * Option step which allows to specify the [AssertionGroup.description] -- [RawString.EMPTY] is
 * used as [AssertionGroup.representation].
 */
interface AssertionGroupDescriptionAndEmptyRepresentationOption<out T: AssertionGroupType, R> {
    /**
     * The previously defined [AssertionGroup.type].
     */
    val groupType: T


    /**
     * Uses the given [description] as [AssertionGroup.description] and [RawString.EMPTY] as [AssertionGroup.representation].
     */
    fun withDescription(description: Translatable): R

    companion object {
        fun <T: AssertionGroupType, R> create(
            type: T,
            factory: (T, Translatable, Any) -> R
        ): AssertionGroupDescriptionAndEmptyRepresentationOption<T, R> = AssertionGroupDescriptionAndEmptyRepresentationOptionImpl(type, factory)
    }
}
