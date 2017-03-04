package ch.tutteli.assertk.assertions

data class FeatureAssertionGroup(
    override val featureName: String,
    override val subSubject: Any,
    override val assertions: List<IAssertion>) : IFeatureAssertionGroup
