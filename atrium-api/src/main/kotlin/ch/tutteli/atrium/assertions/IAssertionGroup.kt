package ch.tutteli.atrium.assertions

/**
 * The base interface for [IAssertion] groups, providing a default implementation for [IAssertion.holds]
 * which returns true if all its [assertions] hold.
 */
interface IAssertionGroup : IAssertion {
    val name: String
    val subject: Any
    val assertions: List<IAssertion>

    /**
     * Holds if all its [assertions] hold.
     */
    override fun holds() = assertions.all(IAssertion::holds)
}
