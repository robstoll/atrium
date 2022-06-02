package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * The base interface for [Assertion] groups, providing a default implementation for [Assertion.holds]
 * which returns `true` if all its [assertions] hold.
 */
interface AssertionGroup : Assertion {

    /**
     * The description of the group.
     */
    val description: Translatable

    /**
     * The type of the group, e.g. [RootAssertionGroupType].
     */
    val type: AssertionGroupType


    /**
     * A complementing representation to the description -- typically the subject for which the [assertions]
     * are defined.
     *
     * For instance, if the description is `index 0` then the representation shows what is at index 0.
     */
    val representation: Any

    /**
     * The assertions of this group, which are defined for the subject represented by [representation].
     */
    val assertions: List<Assertion>

    /**
     * Holds if all its [assertions] hold.
     *
     * @return `true` if all [assertions] hold; `false` otherwise.
     */
    override fun holds() = assertions.all(Assertion::holds)
}
