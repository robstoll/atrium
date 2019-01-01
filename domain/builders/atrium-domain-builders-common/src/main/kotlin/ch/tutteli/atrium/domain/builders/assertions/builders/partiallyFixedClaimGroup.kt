package ch.tutteli.atrium.domain.builders.assertions.builders

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.BasicAssertionGroupFinalStep
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.PartiallyFixedClaimAssertionGroupFinalStepImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.PartiallyFixedClaimAssertionGroupHoldsOptionImpl
import ch.tutteli.atrium.domain.builders.assertions.builders.impl.PartiallyFixedClaimAssertionGroupTypeOptionImpl
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 *  Builder to create an [AssertionGroup] whose [AssertionGroup.holds] is a logic AND operation composed by a fixed
 *  part and its [AssertionGroup.assertions].
 *
 * The intended use case is if the [AssertionGroup.assertions] depend on a pre-transformation (e.g., extract a feature,
 * type transformation etc.) which can either be successful or fail.
 * If it fails, then the assertions cannot be carried out (only be used for explanation) and in such a case
 * [AssertionGroup.holds] has to be false. Otherwise [AssertionGroup.holds] depend on [AssertionGroup.assertions].
 */
@Suppress("unused")
val AssertionBuilder.partiallyFixedClaimGroup: PartiallyFixedClaimAssertionGroupTypeOption
    get() = PartiallyFixedClaimAssertionGroupTypeOptionImpl

interface PartiallyFixedClaimAssertionGroupTypeOption: FixedClaimLikeAssertionGroupTypeOption<PartiallyFixedClaimAssertionGroupFinalStep>

interface PartiallyFixedClaimAssertionGroupHoldsOption<T : AssertionGroupType> : FixedClaimLikeAssertionGroupHoldsOption<T, PartiallyFixedClaimAssertionGroupFinalStep>{
    companion object {
        fun <T: AssertionGroupType> create(groupType: T): PartiallyFixedClaimAssertionGroupHoldsOption<T>
            = PartiallyFixedClaimAssertionGroupHoldsOptionImpl(groupType)
    }
}

/**
 * Final step which creates an [AssertionGroup] whose [AssertionGroup.holds] is a logic AND operation composed by
 * [preTransformationHolds] and its [AssertionGroup.assertions].
 */
interface PartiallyFixedClaimAssertionGroupFinalStep : BasicAssertionGroupFinalStep {
    /**
     * The previously defined state of the pre-transformation (if it holds or not).
     */
    val preTransformationHolds: Boolean

    companion object {
        fun create(
            groupType: AssertionGroupType,
            description: Translatable,
            representation: Any,
            assertions: List<Assertion>,
            holds: Boolean
        ): PartiallyFixedClaimAssertionGroupFinalStep
            = PartiallyFixedClaimAssertionGroupFinalStepImpl(groupType, description, representation, assertions, holds)
    }
}
