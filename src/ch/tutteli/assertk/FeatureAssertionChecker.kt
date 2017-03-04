package ch.tutteli.assertk

import ch.tutteli.assertk.assertions.FeatureAssertionGroup
import ch.tutteli.assertk.assertions.IAssertion

class FeatureAssertionChecker<out T : Any>(private val subjectFactory: IAssertionFactory<T>) : IAssertionCheckerDelegateFail, IAssertionChecker {

    override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
        val featureAssertionGroup = FeatureAssertionGroup(featureName = assertionVerb, subSubject = subject, assertions = assertions)
        subjectFactory.addAssertion(featureAssertionGroup)
    }
}
