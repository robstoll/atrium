package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs


/**
 * Provides options to create an [AssertionGroup] with a certain [ExplanatoryAssertionGroupType].
 */
interface ExplanatoryAssertionGroupOption {

    /**
     * Builder to create an [AssertionGroup] with a [DefaultExplanatoryAssertionGroupType].
     */
    val withDefault: ExplanatoryAssertionGroupBuilder<DefaultExplanatoryAssertionGroupType>

    /**
     * Builder to create an [AssertionGroup] with a [WarningAssertionGroupType].
     */
    val withWarning: ExplanatoryAssertionGroupBuilder<WarningAssertionGroupType>

    /**
     * Builder to create an [AssertionGroup] with a custom [ExplanatoryAssertionGroupType].
     */
    fun <T: ExplanatoryAssertionGroupType> withType(groupType: T): ExplanatoryAssertionGroupBuilder<T>
}

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
     * See [ExplanatoryAssertionBuilderImpl.create] for details.
     */
    fun createWithExplanatoryAssertion(translatable: Translatable, arg: Any, vararg otherArgs: Any): AssertionGroup
        = create(assertionBuilder.explanatory.create(translatable, arg, *otherArgs))

    /**
     * Creates the [AssertionGroup] with the previously specified [groupType] using the given [translatable]
     * to create an [ExplanatoryAssertion] which is used as single [Assertion] in [AssertionGroup.assertions].
     *
     * See [ExplanatoryAssertionBuilderImpl.create] for details.
     */
    fun createWithExplanatoryAssertion(translatable: Translatable): AssertionGroup
        = create(assertionBuilder.explanatory.create(translatable))
}


/**
 * Provides options to create an [AssertionGroup] with a certain [ExplanatoryAssertionGroupType].
 */
internal object ExplanatoryAssertionGroupOptionImpl : ExplanatoryAssertionGroupOption {

    override val withDefault get()
        = ExplanatoryAssertionGroupBuilderImpl(DefaultExplanatoryAssertionGroupType)

    override val withWarning get()
        = ExplanatoryAssertionGroupBuilderImpl(WarningAssertionGroupType)

    override fun <T: ExplanatoryAssertionGroupType> withType(groupType: T)
        = ExplanatoryAssertionGroupBuilderImpl(groupType)
}

/**
 * Builder to create an [AssertionGroup] with the given [groupType] (an [ExplanatoryAssertionGroupType]).
 */
internal class ExplanatoryAssertionGroupBuilderImpl<out T: ExplanatoryAssertionGroupType>(
    override val groupType: T
) : ExplanatoryAssertionGroupBuilder<T> {

    override fun create(assertions: List<Assertion>): AssertionGroup
        = ExplanatoryAssertionGroup(groupType, assertions)
}
