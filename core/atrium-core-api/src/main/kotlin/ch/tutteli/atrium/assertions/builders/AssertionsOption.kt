package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.AssertionsOptionImpl
import ch.tutteli.atrium.assertions.builders.impl.BasicAssertionGroupFinalStepImpl
import ch.tutteli.atrium.reporting.RawString
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Base interface for all [AssertionGroup] builder which want to provide different overloads for [withAssertion] methods.
 */
interface AssertionsOption<out T : AssertionGroupType, R> {
    /**
     * The [AssertionGroupType] which shall be used for [AssertionGroup.type].
     */
    val groupType: T

    /**
     * The [AssertionGroup.name].
     */
    val description: Translatable

    /**
     * The [AssertionGroup.representation].
     */
    val representation: Any


    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertion] as single [AssertionGroup.assertions].
     */
    fun withAssertion(assertion: Assertion): R = withAssertions(listOf(assertion))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertion1] and [assertion2] as [AssertionGroup.assertions].
     */
    fun withAssertions(assertion1: Assertion, assertion2: Assertion): R = withAssertions(listOf(assertion1, assertion2))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertion1], [assertion2] and [assertion3] as [AssertionGroup.assertions].
     */
    fun withAssertions(assertion1: Assertion, assertion2: Assertion, assertion3: Assertion): R
        = withAssertions(listOf(assertion1, assertion2, assertion3))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertions] as [AssertionGroup.assertions].
     */
    fun withAssertions(assertions: List<Assertion>): R

    companion object {
        fun <T : AssertionGroupType, R> create(
            groupType: T,
            description: Translatable,
            representation: Any,
            factory: (T, Translatable, Any, List<Assertion>) -> R
        ): AssertionsOption<T, R> = AssertionsOptionImpl(groupType, description, representation, factory)

        fun <T : AssertionGroupType, R> withEmptyDescriptionAndRepresentation(
            groupType: T,
            factory: (T, Translatable, Any, List<Assertion>) -> R
        ): AssertionsOption<T, R> = AssertionsOptionImpl(groupType, Untranslatable.EMPTY, RawString.EMPTY, factory)

        fun <T : AssertionGroupType> withDefaultFinalStepAndEmptyDescriptionAndRepresentation(
            groupType: T
        ): AssertionsOption<T, BasicAssertionGroupFinalStep>
            = AssertionsOptionImpl(groupType, Untranslatable.EMPTY, RawString.EMPTY, ::BasicAssertionGroupFinalStepImpl)

        fun <T : AssertionGroupType> asFactoryWithDefaultFinalStep(): (T, Translatable, Any) -> AssertionsOption<T, BasicAssertionGroupFinalStep>
            = { t, d, r ->
                AssertionsOptionImpl(t, d, r, ::BasicAssertionGroupFinalStepImpl)
            }
    }
}
