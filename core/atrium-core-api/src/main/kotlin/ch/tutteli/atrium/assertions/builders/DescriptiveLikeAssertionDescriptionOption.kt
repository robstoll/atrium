package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.impl.DescriptiveLikeAssertionDescriptionOptionImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Optionsa descriptive like assertion (such as [DescriptiveAssertion]).
 */
interface DescriptiveLikeAssertionDescriptionOption<R> {
    /**
     * The previously defined test which is used to determine [DescriptiveAssertion.holds].
     */
    val test: () -> Boolean

    /**
     * Uses the given [description] as [AssertionGroup.name] and [representation] as [AssertionGroup.representation].
     */
    fun withDescriptionAndRepresentation(description: String, representation: Any): R
        = withDescriptionAndRepresentation(Untranslatable(description), representation)

    /**
     * Uses the given [description] as [AssertionGroup.name] and [representation] as [AssertionGroup.representation]
     * unless [representation] is null in which case [RawString.NULL] is used.
     */
    fun withDescriptionAndNullableRepresentation(description: String, representation: Any?): R
        = withDescriptionAndNullableRepresentation(Untranslatable(description), representation)

    /**
     * Uses the given [description] as [DescriptiveAssertion.description] and [representation]
     * as [DescriptiveAssertion.representation] unless [representation] is null in which case [RawString.NULL] is used.
     */
    fun withDescriptionAndNullableRepresentation(description: Translatable, representation: Any?): R
        = withDescriptionAndRepresentation(description, representation ?: RawString.NULL)

    /**
     * Uses the given [description] as [AssertionGroup.name] and [representation] as [AssertionGroup.representation].
     */
    fun withDescriptionAndRepresentation(description: Translatable, representation: Any): R

    companion object {
        fun <R> create(
            test: () -> Boolean,
            factory: (() -> Boolean, Translatable, Any) -> R
        ): DescriptiveLikeAssertionDescriptionOption<R> = DescriptiveLikeAssertionDescriptionOptionImpl(test, factory)
    }
}
