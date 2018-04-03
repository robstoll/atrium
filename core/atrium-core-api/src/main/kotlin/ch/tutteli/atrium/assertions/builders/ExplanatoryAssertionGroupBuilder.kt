package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.ExplanatoryAssertion
import ch.tutteli.atrium.assertions.ExplanatoryAssertionGroupType
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Builder to create an [AssertionGroup] with the given [groupType] (an [ExplanatoryAssertionGroupType]).
 */
interface ExplanatoryAssertionGroupBuilder<out T: ExplanatoryAssertionGroupType>: AssertionGroupBuilder<T> {

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given [translatable]
     * -- which is used in an [TranslatableWithArgs] together with the given arguments
     * ([arg] and optionally [otherArgs]) -- to create an [ExplanatoryAssertion]
     * which is used as single [Assertion] in [AssertionGroup.assertions].
     *
     * See [AssertionGroupBuilder.create] for details.
     */
    fun createWithExplanatoryAssertion(translatable: Translatable, arg: Any, vararg otherArgs: Any): AssertionGroup
        = create(assertionBuilder.explanatory.create(translatable, arg, *otherArgs))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given [translatable]
     * to create an [ExplanatoryAssertion] which is used as single [Assertion] in [AssertionGroup.assertions].
     *
     * See [AssertionGroupBuilder.create] for details.
     */
    fun createWithExplanatoryAssertion(translatable: Translatable): AssertionGroup
        = create(assertionBuilder.explanatory.create(translatable))
}
