package ch.tutteli.assertk.assertions

import ch.tutteli.assertk.assertions.IAssertion

data class FeatureAssertionGroup(
    override val featureName: String,
    override val subSubject: Any,
    override val assertions: List<IAssertion>) : IFeatureAssertionGroup
