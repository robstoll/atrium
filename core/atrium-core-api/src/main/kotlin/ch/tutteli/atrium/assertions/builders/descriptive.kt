package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.impl.DescriptiveAssertionFinalStepImpl
import ch.tutteli.atrium.assertions.builders.impl.DescriptiveLikeAssertionDescriptionOptionImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Options to define [DescriptiveAssertion.holds].
 */
interface DescriptiveAssertionHoldsOption {

    /**
     * Defines a constant failing assertion.
     */
    val failing: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
    /**
     * Defines a constant holding assertion.
     */
    val holding: DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>

    /**
     * Whether the assertion holds or not is defined by the given [test].
     */
    fun withTest(test: () -> Boolean): DescriptiveLikeAssertionDescriptionOption<DescriptiveAssertionFinalStep>
}

/**
 * Options descriptive like assertion (such as [DescriptiveAssertion]).
 */
interface DescriptiveLikeAssertionDescriptionOption<R> {
    /**
     * The previously defined test which is used to determine [DescriptiveAssertion.holds].
     */
    val test: () -> Boolean

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

interface DescriptiveAssertionFinalStep : AssertionBuilderFinalStep<DescriptiveAssertion> {
    /**
     * The previously defined test which is used to determine [DescriptiveAssertion.holds].
     */
    val test: () -> Boolean

    /**
     * The [DescriptiveAssertion.description].
     */
    val description: Translatable

    /**
     * The [DescriptiveAssertion.representation].
     */
    val representation: Any

    companion object {
        fun create(
            test: () -> Boolean,
            description: Translatable,
            representation: Any
        ): DescriptiveAssertionFinalStep = DescriptiveAssertionFinalStepImpl(test, description, representation)
    }
}

