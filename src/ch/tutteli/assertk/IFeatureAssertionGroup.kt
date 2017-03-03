package ch.tutteli.assertk

interface IFeatureAssertionGroup: IAssertion{
    val featureName: String
    val subSubject: Any
    val assertions: List<IAssertion>

    override fun holds() = assertions.all(IAssertion::holds)
}
