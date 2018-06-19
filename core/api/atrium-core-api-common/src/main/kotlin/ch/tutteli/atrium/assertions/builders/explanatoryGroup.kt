package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.impl.ExplanatoryAssertionGroupFinalStepImpl
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Option step which allows to specify what [ExplanatoryAssertionGroupType] is used as [AssertionGroup.type].
 */
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
interface ExplanatoryAssertionGroupFinalStep: AssertionBuilderFinalStep<AssertionGroup>{
    /**
     * The previously defined [AssertionGroup.type].
     */
    val groupType: ExplanatoryAssertionGroupType

    /**
     * The previously defined [AssertionGroup.assertions]
     */
    val explanatoryAssertions: List<Assertion>

    companion object {
        fun create(
            groupType: ExplanatoryAssertionGroupType,
            explanatoryAssertions: List<Assertion>
        ): ExplanatoryAssertionGroupFinalStep = ExplanatoryAssertionGroupFinalStepImpl(groupType, explanatoryAssertions)
    }
}

/**
 * Creates the [AssertionGroup] with the previously specified [AssertionsOption.groupType] using the given
 * [translatable] to create an [ExplanatoryAssertion] which is used as single
 * [Assertion] in [AssertionGroup.assertions].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
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
fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryAssertionGroupFinalStep>.withExplanatoryAssertions(
    translatable: Translatable,
    arg: Any,
    vararg otherArgs: Any
): ExplanatoryAssertionGroupFinalStep
    = withAssertion(assertionBuilder.explanatory.withDescription(translatable, arg, *otherArgs).build())
