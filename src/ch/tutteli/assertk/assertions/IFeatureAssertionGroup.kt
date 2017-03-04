package ch.tutteli.assertk.assertions

import ch.tutteli.assertk.assertions.IAssertion

interface IFeatureAssertionGroup: IAssertion {
    val featureName: String
    val subSubject: Any
    val assertions: List<IAssertion>

    override fun holds() = assertions.all(IAssertion::holds)
}
