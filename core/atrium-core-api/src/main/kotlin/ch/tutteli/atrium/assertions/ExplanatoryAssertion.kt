package ch.tutteli.atrium.assertions

import  ch.tutteli.atrium.reporting.ObjectFormatter

/**
 * Represents an [Assertion] which is typically used in an [AssertionGroup] with an [ExplanatoryAssertionGroupType]
 * and provides an [explanation] which is typically formatted by an [ObjectFormatter] in reporting.
 *
 * It is not of importance whether the assertions holds or not; thus it overrides [holds] which always returns `true`.
 */
interface ExplanatoryAssertion : Assertion {
    /**
     * The object used to explain something.
     *
     * Can also be `null` where an [ObjectFormatter] should use the representation for `null` in such a case.
     */
    val explanation: Any?

    /**
     * Always `true` since it should not matter whether an [ExplanatoryAssertion] holds or not.
     */
    override fun holds() = true
}
