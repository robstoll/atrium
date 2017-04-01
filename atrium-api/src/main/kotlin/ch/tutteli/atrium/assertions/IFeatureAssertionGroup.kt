package ch.tutteli.atrium.assertions

/**
 * The base interface for feature [IAssertion] groups, providing a default implementation for [IAssertion.holds]
 * which returns true if all its [assertions] hold.
 */
interface IFeatureAssertionGroup : IAssertion {
    val featureName: String
    val subSubject: Any
    val assertions: List<IAssertion>

    /**
     * Holds if all its [assertions] hold.
     */
    override fun holds() = assertions.all(IAssertion::holds)
}
