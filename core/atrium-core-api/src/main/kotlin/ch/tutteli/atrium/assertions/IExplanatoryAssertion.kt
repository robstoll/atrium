package ch.tutteli.atrium.assertions

import  ch.tutteli.atrium.reporting.ObjectFormatter

/**
 * Represents an [IAssertion] which is typically used in an [IAssertionGroup] with a [IExplanatoryAssertionGroupType]
 * and provides an [explanation] which is typically formatted by an [ObjectFormatter] in reporting.
 *
 * It is not of importance whether the assertions holds or not and thus it overrides [holds] and return always `true`.
 */
interface IExplanatoryAssertion : IAssertion {
    /**
     * The object used to explain something.
     */
    val explanation: Any?

    /**
     * Always true since it should not matter whether an [IExplanatoryAssertion] holds or not.
     */
    override fun holds() = true
}
