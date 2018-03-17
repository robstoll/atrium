package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs


/**
 * Provides options to create an [AssertionGroup] with a certain [ExplanatoryAssertionGroupType].
 */
interface ExplanatoryAssertionGroupOption {

    /**
     * Builder to create an [AssertionGroup] with a [DefaultListAssertionGroupType].
     */
    val withDefault: ExplanatoryAssertionGroupBuilder

    /**
     * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
     */
    val withWarning: ExplanatoryAssertionGroupBuilder

    /**
     * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
     */
    fun withType(groupType: ExplanatoryAssertionGroupType): ExplanatoryAssertionGroupBuilder
}

/**
 * Builder to create an [AssertionGroup] with the given [groupType] (an [ExplanatoryAssertionGroupType]).
 */
interface ExplanatoryAssertionGroupBuilder {

    /**
     * The [AssertionGroupType] which shall be used for the [AssertionGroup].
     */
    val groupType: ExplanatoryAssertionGroupType

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given [translatable]
     * -- which is used in an [TranslatableWithArgs] together with the given arguments
     * ([arg] and optionally [otherArgs]) -- to create an [ExplanatoryAssertion]
     * which is used as single [Assertion] in [AssertionGroup.assertions].
     *
     * See [ExplanatoryAssertionBuilderImpl.create] for details.
     */
    fun createWithExplanatoryAssertion(translatable: Translatable, arg: Any, vararg otherArgs: Any): AssertionGroup

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given [translatable]
     * to create an [ExplanatoryAssertion] which is used as single [Assertion] in [AssertionGroup.assertions].
     *
     * See [ExplanatoryAssertionBuilderImpl.create] for details.
     */
    fun createWithExplanatoryAssertion(translatable: Translatable): AssertionGroup

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertion] as single [Assertion] in [AssertionGroup.assertions].
     */
    fun create(assertion: Assertion): AssertionGroup

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given
     * [assertions] as [AssertionGroup.assertions].
     */
    fun create(assertions: List<Assertion>): AssertionGroup
}


/**
 * Provides options to create an [AssertionGroup] with a certain [ExplanatoryAssertionGroupType].
 */
internal object ExplanatoryAssertionGroupOptionImpl : ExplanatoryAssertionGroupOption {

    override val withDefault get()
        = ExplanatoryAssertionGroupBuilderImpl(DefaultExplanatoryAssertionGroupType)

    override val withWarning get()
        = ExplanatoryAssertionGroupBuilderImpl(WarningAssertionGroupType)

    override fun withType(groupType: ExplanatoryAssertionGroupType)
        = ExplanatoryAssertionGroupBuilderImpl(groupType)
}

/**
 * Builder to create an [AssertionGroup] with the given [groupType] (an [ExplanatoryAssertionGroupType]).
 */
internal class ExplanatoryAssertionGroupBuilderImpl internal constructor(
    override val groupType: ExplanatoryAssertionGroupType
) : ExplanatoryAssertionGroupBuilder {

    override fun createWithExplanatoryAssertion(translatable: Translatable, arg: Any, vararg otherArgs: Any)
        = create(assertionBuilder.explanatory.create(translatable, arg, *otherArgs))

    override fun createWithExplanatoryAssertion(translatable: Translatable)
        = create(assertionBuilder.explanatory.create(translatable))

    override fun create(assertion: Assertion): AssertionGroup
        = create(listOf(assertion))

    override fun create(assertions: List<Assertion>): AssertionGroup
        = ExplanatoryAssertionGroup(groupType, assertions)
}
