package ch.tutteli.assertk

class FeatureAssertionChecker<out T : Any>(private val subjectFactory: IAssertionFactory<T>) : IAssertionCheckerDelegateFail, IAssertionChecker {

    override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
        val featureAssertionGroup = FeatureAssertionGroup(featureName = assertionVerb, subSubject = subject, assertions = assertions)
        subjectFactory.addAssertion(featureAssertionGroup)
    }
}
