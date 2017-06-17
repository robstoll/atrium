package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * The base interface for [IAssertion] groups, providing a default implementation for [IAssertion.holds]
 * which returns `true` if all its [assertions] hold.
 */
interface IAssertionGroup : IAssertion {
    /**
     * The name of the group.
     */
    val name: ITranslatable
    /**
     * The type of the group, e.g. [RootAssertionGroupType].
     */
    val type: IAssertionGroupType

    /**
     * The subject for which the [assertions] are defined.
     */
    val subject: Any
    /**
     * The assertions of this group, which are defined for [subject].
     */
    val assertions: List<IAssertion>

    /**
     * Holds if all its [assertions] hold.
     *
     * @return `true` if all [assertions] hold; `false` otherwise.
     */
    override fun holds() = assertions.all(IAssertion::holds)
}
