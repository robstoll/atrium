package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.impl.explanatoryGroup.FinalStepImpl
import ch.tutteli.atrium.assertions.builders.impl.explanatoryGroup.GroupTypeOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Defines the contract to build an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
 */
interface ExplanatoryGroup {

    /**
     * Option step which allows to specify what [ExplanatoryAssertionGroupType] is used as [AssertionGroup.type].
     */
    @Suppress("DEPRECATION" /* TODO remove super-type with 1.0.0 */)
    interface GroupTypeOption: ExplanatoryAssertionGroupTypeOption {

        /**
         * Builder to create an [AssertionGroup] with a [DefaultExplanatoryAssertionGroupType].
         */
        override val withDefaultType: AssertionsOption<DefaultExplanatoryAssertionGroupType, FinalStep>

        /**
         * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
         */
        override val withWarningType: AssertionsOption<WarningAssertionGroupType, FinalStep>

        /**
         * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
         */
        override fun <T : ExplanatoryAssertionGroupType> withType(groupType: T): AssertionsOption<T, FinalStep>

        companion object {
            /**
             * Factory method to create the [GroupTypeOption] step in the building process
             * of an [AssertionGroup] with an [ExplanatoryAssertionGroupType].
             */
            fun create(): GroupTypeOption = GroupTypeOptionImpl
        }
    }

    /**
     * Final step which creates an [AssertionGroup] with an [ExplanatoryAssertionGroupType] based on the previously
     * defined [groupType] and the [explanatoryAssertions].
     */
    @Suppress("DEPRECATION" /* TODO remove super-type with 1.0.0 */)
    interface FinalStep: AssertionBuilderFinalStep<AssertionGroup>, ExplanatoryAssertionGroupFinalStep{
        /**
         * The previously defined [AssertionGroup.type].
         */
        override val groupType: ExplanatoryAssertionGroupType

        /**
         * The previously defined [AssertionGroup.assertions]
         */
        override val explanatoryAssertions: List<Assertion>

        companion object {
            /**
             * Factory method to create the [FinalStep] in the building process of [AssertionGroup] with an
             * [ExplanatoryAssertionGroupType] (specified by the given [groupType]) and [explanatoryAssertions].
             */
            fun create(
                groupType: ExplanatoryAssertionGroupType,
                explanatoryAssertions: List<Assertion>
            ): FinalStep = FinalStepImpl(groupType, explanatoryAssertions)
        }
    }
}

/**
 * Creates the [AssertionGroup] with the previously specified [AssertionsOption.groupType] using the given
 * [translatable] to create an [ExplanatoryAssertion] which is used as single
 * [Assertion] in [AssertionGroup.assertions].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
@Suppress("DEPRECATION" /* TODO exchange ExplanatoryAssertionGroupFinalStep with ExplanatoryGroup.FinalStep in 1.0.0 */ )
fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryAssertionGroupFinalStep>.withExplanatoryAssertion(
    translatable: Translatable
): ExplanatoryAssertionGroupFinalStep
    = withAssertion(assertionBuilder.explanatory.withDescription(translatable).build())

/**
 * Creates the [AssertionGroup] with the previously specified [AssertionsOption.groupType] using the given
 * [translatable] -- which is used in an [TranslatableWithArgs] together with the given arguments ([arg] and
 * optionally [otherArgs]) -- to create an [ExplanatoryAssertion] which is used as single
 * [Assertion] in [AssertionGroup.assertions].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
@Suppress("DEPRECATION" /* TODO exchange ExplanatoryAssertionGroupFinalStep with ExplanatoryGroup.FinalStep in 1.0.0 */ )
fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryAssertionGroupFinalStep>.withExplanatoryAssertions(
    translatable: Translatable,
    arg: Any,
    vararg otherArgs: Any
): ExplanatoryAssertionGroupFinalStep
    = withAssertion(assertionBuilder.explanatory.withDescription(translatable, arg, *otherArgs).build())



/**
 * Option step which allows to specify what [ExplanatoryAssertionGroupType] is used as [AssertionGroup.type].
 */
@Suppress("DEPRECATION" /* TODO remove whole interface with 1.0.0 */)
@Deprecated("Use ExplanatoryGroup.GroupTypeOption instead; will be removed with 1.0.0", ReplaceWith("ExplanatoryGroup.GroupTypeOption"))
interface ExplanatoryAssertionGroupTypeOption {

    /**
     * Builder to create an [AssertionGroup] with a [DefaultExplanatoryAssertionGroupType].
     */
    val withDefaultType: AssertionsOption<DefaultExplanatoryAssertionGroupType, ExplanatoryAssertionGroupFinalStep>

    /**
     * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
     */
    val withWarningType: AssertionsOption<WarningAssertionGroupType, ExplanatoryAssertionGroupFinalStep>

    /**
     * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
     */
    fun <T : ExplanatoryAssertionGroupType> withType(groupType: T): AssertionsOption<T, ExplanatoryAssertionGroupFinalStep>
}

/**
 * Final step which creates an [AssertionGroup] with an [ExplanatoryAssertionGroupType] based on the previously
 * defined [groupType] and the [explanatoryAssertions].
 */
@Deprecated("Use ExplanatoryGroup.FinalStep instead; will be removed with 1.0.0", ReplaceWith("ExplanatoryGroup.FinalStep"))
interface ExplanatoryAssertionGroupFinalStep: AssertionBuilderFinalStep<AssertionGroup>{
    /**
     * The previously defined [AssertionGroup.type].
     */
    val groupType: ExplanatoryAssertionGroupType

    /**
     * The previously defined [AssertionGroup.assertions]
     */
    val explanatoryAssertions: List<Assertion>

    @Suppress("DEPRECATION" /* TODO remove whole interface with 1.0.0 */)
    companion object {
        fun create(
            groupType: ExplanatoryAssertionGroupType,
            explanatoryAssertions: List<Assertion>
        ): ExplanatoryAssertionGroupFinalStep = FinalStepImpl(groupType, explanatoryAssertions)
    }
}
