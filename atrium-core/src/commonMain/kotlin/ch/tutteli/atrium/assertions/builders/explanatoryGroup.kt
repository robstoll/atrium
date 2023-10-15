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
    interface GroupTypeOption {

        /**
         * Builder to create an [AssertionGroup] with a [DefaultExplanatoryAssertionGroupType].
         */
        val withDefaultType: AssertionsOption<DefaultExplanatoryAssertionGroupType, FinalStep>

        /**
         * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
         */
        val withWarningType: AssertionsOption<WarningAssertionGroupType, FinalStep>

        /**
         * Builder to create an [AssertionGroup] with a [HintAssertionGroupType].
         * @since 1.0.0
         */
        val withHintType: AssertionsOption<HintAssertionGroupType, FinalStep>

        /**
         * Builder to create an [AssertionGroup] with an [InformationAssertionGroupType].
         */
        fun withInformationType(withIndent: Boolean): AssertionsOption<InformationAssertionGroupType, FinalStep>

        /**
         * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
         */
        fun <T : ExplanatoryAssertionGroupType> withType(groupType: T): AssertionsOption<T, FinalStep>

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
    interface FinalStep : AssertionBuilderFinalStep<AssertionGroup> {
        /**
         * The previously defined [AssertionGroup.type].
         */
        val groupType: ExplanatoryAssertionGroupType

        /**
         * The previously defined [AssertionGroup.assertions]
         */
        val explanatoryAssertions: List<Assertion>

        /**
         * Make the ExplanatoryGroup no longer hold, use this if the explanatory expectation-group is a single assertion
         * within a group.
         */
        val failing: FinalStep get() = create(groupType, explanatoryAssertions, holds = false)

        companion object {
            /**
             * Factory method to create the [FinalStep] in the building process of [AssertionGroup] with an
             * [ExplanatoryAssertionGroupType] (specified by the given [groupType]) and [explanatoryAssertions].
             */
            fun create(
                groupType: ExplanatoryAssertionGroupType,
                explanatoryAssertions: List<Assertion>,
                holds: Boolean = true
            ): FinalStep = FinalStepImpl(groupType, explanatoryAssertions, holds)
        }
    }
}

/**
 * Defines that an [ExplanatoryAssertion] will be used as single [Assertion] in [AssertionGroup.assertions] where
 * the given [translatable] is used as [ExplanatoryAssertion.explanation].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryGroup.FinalStep>.withExplanatoryAssertion(
    translatable: Translatable
): ExplanatoryGroup.FinalStep = withExplanatoryAssertion { it.withExplanation(translatable).build() }


/**
 * Defines that an [ExplanatoryAssertion] will be used as single [Assertion] in [AssertionGroup.assertions] where
 * the given [representation] is used as [ExplanatoryAssertion.explanation].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryGroup.FinalStep>.withExplanatoryAssertion(
    representation: Any?
): ExplanatoryGroup.FinalStep = withExplanatoryAssertion { it.withExplanation(representation).build() }

/**
 * Defines that an [ExplanatoryAssertion] will be used as single [Assertion] in [AssertionGroup.assertions] where
 * the given [translatable] -- which is used in an [TranslatableWithArgs] together with the given arguments ([arg] and
 * optionally [otherArgs]) -- is used  as [ExplanatoryAssertion.explanation].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryGroup.FinalStep>.withExplanatoryAssertion(
    translatable: Translatable,
    arg: Any,
    vararg otherArgs: Any
): ExplanatoryGroup.FinalStep = withExplanatoryAssertion { it.withExplanation(translatable, arg, *otherArgs).build() }


/**
 * Defines that an [ExplanatoryAssertion] will be used as single [Assertion] in [AssertionGroup.assertions] where
 * one can configure the [ExplanatoryAssertion] via the given [explanationStep].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
inline fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryGroup.FinalStep>.withExplanatoryAssertion(
    explanationStep: (Explanatory.ExplanationOption) -> Assertion
): ExplanatoryGroup.FinalStep = withAssertion(explanationStep(assertionBuilder.explanatory))
