package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The base interface for [Assertion] groups, providing a default implementation for [Assertion.holds]
 * which returns `true` if all its [assertions] hold.
 */
interface AssertionGroup : Assertion {
    /**
     * The name of the group.
     */
    val name: Translatable
    /**
     * The type of the group, e.g. [RootAssertionGroupType].
     */
    val type: AssertionGroupType

    /**
     * The subject for which the [assertions] are defined.
     */
    val subject: Any
    /**
     * The assertions of this group, which are defined for [subject].
     */
    val assertions: List<Assertion>

    /**
     * Holds if all its [assertions] hold.
     *
     * @return `true` if all [assertions] hold; `false` otherwise.
     */
    override fun holds() = assertions.all(Assertion::holds)
}
