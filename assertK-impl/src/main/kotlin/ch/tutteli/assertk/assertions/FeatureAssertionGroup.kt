package ch.tutteli.assertk.assertions

/**
 * Represent a group of [IAssertion] identified by a [featureName] and a belonging [subSubject].
 */
data class FeatureAssertionGroup(
    override val featureName: String,
    override val subSubject: Any,
    override val assertions: List<IAssertion>) : IFeatureAssertionGroup
