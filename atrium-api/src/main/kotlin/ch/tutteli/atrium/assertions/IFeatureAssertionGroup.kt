package ch.tutteli.atrium.assertions

/**
 * The base interface for feature [IAssertion] groups, providing a default implementation for [IAssertion.holds]
 * which returns `true` if all its [assertions] hold.
 */
interface IFeatureAssertionGroup : IAssertion {
    /**
     * The name of the feature.
     */
    val featureName: String
    /**
     * The feature itself for which the [assertions] are defined for.
     */
    val feature: Any
    /**
     * The assertions of this group, which are defined for [feature].
     */
    val assertions: List<IAssertion>

    /**
     * Holds if all its [assertions] hold.
     *
     * @return `true` if all [assertions] hold; `false` otherwise.
     */
    override fun holds() = assertions.all(IAssertion::holds)
}
