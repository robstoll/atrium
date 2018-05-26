package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs


/**
 * Creates the [AssertionGroup] with the previously specified [AssertionsOption.groupType] using the given
 * [translatable] -- which is used in an [TranslatableWithArgs] together with the given arguments ([arg] and
 * optionally [otherArgs]) -- to create an [ExplanatoryAssertion] which is used as single
 * [Assertion] in [AssertionGroup.assertions].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryAssertionGroupFinalStep>.withExplanatoryAssertion(
    translatable: Translatable,
    arg: Any,
    vararg otherArgs: Any
): ExplanatoryAssertionGroupFinalStep = withAssertion(assertionBuilder.explanatory.create(translatable, arg, *otherArgs))

/**
 * Creates the [AssertionGroup] with the previously specified [AssertionsOption.groupType] using the given
 * [translatable] to create an [ExplanatoryAssertion] which is used as single
 * [Assertion] in [AssertionGroup.assertions].
 *
 * See [AssertionsOption.withAssertion] for details.
 */
fun <T : ExplanatoryAssertionGroupType> AssertionsOption<T, ExplanatoryAssertionGroupFinalStep>.withExplanatoryAssertion(
    translatable: Translatable
): ExplanatoryAssertionGroupFinalStep = withAssertion(assertionBuilder.explanatory.create(translatable))
