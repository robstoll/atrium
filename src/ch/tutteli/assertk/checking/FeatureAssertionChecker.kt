package ch.tutteli.assertk.checking

import ch.tutteli.assertk.checking.IAssertionCheckerDelegateFail
import ch.tutteli.assertk.IAssertionFactory
import ch.tutteli.assertk.assertions.FeatureAssertionGroup
import ch.tutteli.assertk.assertions.IAssertion

class FeatureAssertionChecker<out T : Any>(private val subjectFactory: IAssertionFactory<T>) : IAssertionCheckerDelegateFail, IAssertionChecker {

    override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
        val featureAssertionGroup = FeatureAssertionGroup(featureName = assertionVerb, subSubject = subject, assertions = assertions)
        subjectFactory.addAssertion(featureAssertionGroup)
    }
}
