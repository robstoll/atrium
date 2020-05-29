package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.AssertionGroupType
import ch.tutteli.atrium.assertions.builders.impl.AssertionsOptionImpl
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Option step which allows to specify [AssertionGroup.assertions].
 */
interface AssertionsOption<out T : AssertionGroupType, out R> {
    /**
     * The previously defined [AssertionGroup.type].
     */
    val groupType: T

    /**
     * The previously defined [AssertionGroup.description].
     */
    val description: Translatable

    /**
     * The previously defined [AssertionGroup.representation].
     */
    val representation: Any


    /**
     * Uses the given [assertion] as single [AssertionGroup.assertions].
     */
    fun withAssertion(assertion: Assertion): R = withAssertions(listOf(assertion))

    /**
     * Uses the given [assertion1] and [assertion2] as [AssertionGroup.assertions].
     */
    fun withAssertions(assertion1: Assertion, assertion2: Assertion): R = withAssertions(listOf(assertion1, assertion2))

    /**
     * Uses the given [assertion1], [assertion2] and [assertion3] as [AssertionGroup.assertions].
     */
    fun withAssertions(assertion1: Assertion, assertion2: Assertion, assertion3: Assertion): R =
        withAssertions(listOf(assertion1, assertion2, assertion3))

    /**
     * Uses the given [assertions] as [AssertionGroup.assertions].
     */
    fun withAssertions(assertions: List<Assertion>): R

    companion object {
        /**
         * Factory method to create an [AssertionsOption] step in the building
         * process of an [AssertionGroup] with the given [groupType] where the given [description] and [representation]
         * is used as [AssertionGroup.description]/[AssertionGroup.representation] and the given [factory] to create
         * the next step in the building process.
         */
        fun <T : AssertionGroupType, R> create(
            groupType: T,
            description: Translatable,
            representation: Any,
            factory: (T, Translatable, Any, List<Assertion>) -> R
        ): AssertionsOption<T, R> = AssertionsOptionImpl(groupType, description, representation, factory)

        /**
         * Factory method to create an [AssertionsOption] step in the building
         * process of an [AssertionGroup] with the given [groupType] an empty [AssertionGroup.description] and
         * [AssertionGroup.representation] where the given [factory] is used to create
         * the next step in the building process.
         */
        fun <T : AssertionGroupType, R> withEmptyDescriptionAndRepresentation(
            groupType: T,
            factory: (T, Translatable, Any, List<Assertion>) -> R
        ): AssertionsOption<T, R> = AssertionsOptionImpl(groupType, Untranslatable.EMPTY, Text.EMPTY, factory)

        /**
         * Factory method to create an [AssertionsOption] step in the building
         * process of an [AssertionGroup] with the given [groupType] an empty [AssertionGroup.description] and
         * [AssertionGroup.representation] where [BasicAssertionGroupFinalStep] is used
         * as final step in the building process.
         */
        fun <T : AssertionGroupType> withDefaultFinalStepAndEmptyDescriptionAndRepresentation(
            groupType: T
        ): AssertionsOption<T, BasicAssertionGroupFinalStep> = AssertionsOptionImpl(
            groupType,
            Untranslatable.EMPTY,
            Text.EMPTY,
            BasicAssertionGroupFinalStep.Companion::create
        )

        /**
         * Returns a factory method which creates an [AssertionsOption] where [BasicAssertionGroupFinalStep] is used
         * as final step in the building process.
         */
        fun <T : AssertionGroupType> factoryWithDefaultFinalStep(): (T, Translatable, Any) -> AssertionsOption<T, BasicAssertionGroupFinalStep> {
            @Suppress(
                "UnnecessaryVariable",
                /* that's fine because T is covariant and we do not need multiple function objects for the same functionality */
                "UNCHECKED_CAST"
            )
            val factory =
                factoryWithDefaultFinalStep as (T, Translatable, Any) -> AssertionsOption<T, BasicAssertionGroupFinalStep>
            return factory
        }

        private val factoryWithDefaultFinalStep: (AssertionGroupType, Translatable, Any) -> AssertionsOption<AssertionGroupType, BasicAssertionGroupFinalStep> =
            { t, d, r ->
                AssertionsOptionImpl(t, d, r, BasicAssertionGroupFinalStep.Companion::create)
            }
    }
}
