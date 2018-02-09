package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs

/**
 * Provides options to create an [AssertionGroup] with a certain [ExplanatoryAssertionGroupType].
 */
class ExplanatoryAssertionGroupOption internal constructor() {
    /**
     * Builder to create an [AssertionGroup] with a [DefaultListAssertionGroupType].
     */
    val withDefault =
        ExplanatoryAssertionGroupBuilder(DefaultExplanatoryAssertionGroupType)
    /**
     * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
     */
    val withWarning = ExplanatoryAssertionGroupBuilder(WarningAssertionGroupType)

    /**
     * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
     */
    fun withType(groupType: ExplanatoryAssertionGroupType) =
        ExplanatoryAssertionGroupBuilder(groupType)
}

/**
 * Builder to create an [AssertionGroup] with the given [groupType] (an [ExplanatoryAssertionGroupType]).
 */
class ExplanatoryAssertionGroupBuilder internal constructor(private val groupType: ExplanatoryAssertionGroupType) {

    /**
     * Creates the [AssertionGroup] using the given [translatable] -- which is used in an [TranslatableWithArgs]
     * together with the given arguments ([arg] and optionally [otherArgs]) -- to create an [ExplanatoryAssertion]
     * which is used as single [Assertion] in [AssertionGroup.assertions].
     *
     * See [ExplanatoryAssertionBuilder.create] for details.
     */
    fun createWithExplanatoryAssertion(translatable: Translatable, arg: Any, vararg otherArgs: Any)
        = create(AssertionBuilder.explanatory.create(translatable, arg, *otherArgs))

    /**
     * Creates the [AssertionGroup] using the given [translatable] to create an [ExplanatoryAssertion] which is used
     * as single [Assertion] in [AssertionGroup.assertions].
     *
     * See [ExplanatoryAssertionBuilder.create] for details.
     */
    fun createWithExplanatoryAssertion(translatable: Translatable)
        = create(AssertionBuilder.explanatory.create(translatable))

    /**
     * Creates the [AssertionGroup] using the given [assertion] as single [Assertion] in [AssertionGroup.assertions].
     */
    fun create(assertion: Assertion): AssertionGroup
        = create(listOf(assertion))

    /**
     * Creates the [AssertionGroup] using the given [assertions] as [AssertionGroup.assertions].
     */
    fun create(assertions: List<Assertion>): AssertionGroup
        = ExplanatoryAssertionGroup(groupType, assertions)
}
