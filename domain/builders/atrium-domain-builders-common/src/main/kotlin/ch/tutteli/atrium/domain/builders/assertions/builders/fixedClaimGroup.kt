package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.*
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.FixedClaimAssertionGroupFinalStepImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.FixedClaimAssertionGroupHoldsOptionImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.FixedClaimAssertionGroupTypeOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions]).
 *
 * The intended use case is if all [AssertionGroup.assertions] are [AssertionGroup]s with an
 * [ExplanatoryAssertionGroupType]. Such groups always return `true` for [AssertionGroup.holds] but you might want to
 * explain a complex failing assertion with those groups. In such a use case this builder is your choice.
 */
@Suppress("unused")
val AssertionBuilder.fixedClaimGroup: FixedClaimAssertionGroupTypeOption
    get() = FixedClaimAssertionGroupTypeOptionImpl

interface FixedClaimAssertionGroupTypeOption: FixedClaimLikeAssertionGroupTypeOption<FixedClaimAssertionGroupFinalStep>

interface FixedClaimAssertionGroupHoldsOption<T : AssertionGroupType> : FixedClaimLikeAssertionGroupHoldsOption<T, FixedClaimAssertionGroupFinalStep>{
    companion object {
        fun <T: AssertionGroupType> create(groupType: T): FixedClaimAssertionGroupHoldsOption<T>
            = FixedClaimAssertionGroupHoldsOptionImpl(groupType)
    }
}

/**
 * Final step which creates an [AssertionGroup] whose [AssertionGroup.holds] is fixed (not determined based on its
 * [AssertionGroup.assertions] but on the given [holds]).
 */
interface FixedClaimAssertionGroupFinalStep : BasicAssertionGroupFinalStep {
    /**
     * The previously defined [AssertionGroup.holds].
     */
    val holds: Boolean

    companion object {
        fun create(
            groupType: AssertionGroupType,
            description: Translatable,
            representation: Any,
            assertions: List<Assertion>,
            holds: Boolean
        ): FixedClaimAssertionGroupFinalStep
            = FixedClaimAssertionGroupFinalStepImpl(groupType, description, representation, assertions, holds)
    }
}
