package ch.tutteli.assertk.assertions

interface IFeatureAssertionGroup : IAssertion {
    val featureName: String
    val subSubject: Any
    val assertions: List<IAssertion>

    override fun holds() = assertions.all(IAssertion::holds)
}
