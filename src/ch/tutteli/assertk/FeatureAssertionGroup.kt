package ch.tutteli.assertk

data class FeatureAssertionGroup(
    override val featureName: String,
    override val subSubject: Any,
    override val assertions: List<IAssertion>) : IFeatureAssertionGroup
