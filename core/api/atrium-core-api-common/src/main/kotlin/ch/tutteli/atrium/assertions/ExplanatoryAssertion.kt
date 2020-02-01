package ch.tutteli.atrium.assertions

import  ch.tutteli.atrium.reporting.ObjectFormatter

/**
 * Represents an [Assertion] which can be used to explain/show an instance which is eventually formatted
 * by an [ObjectFormatter] in reporting -- it needs to be a child of an [AssertionGroup]
 * with an [ExplanatoryAssertionGroupType].
 *
 * As it is a child of an [AssertionGroup] with an [ExplanatoryAssertionGroupType], it is not of importance
 * whether the assertions holds or not; thus it overrides [holds] which always returns `true`.
 *
 * This assertion will be turned into a `Reportable` with 1.0.0 and eventually be removed with 1.0.0
 * See https://github.com/robstoll/atrium-roadmap/issues/1 for more information.
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
