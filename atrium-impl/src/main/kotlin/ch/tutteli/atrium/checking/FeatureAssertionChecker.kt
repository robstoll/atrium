package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.FeatureAssertionGroup
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroup
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * An [IAssertionChecker] useful for feature assertions which creates [IFeatureAssertionGroup]s and adds them to the given [subjectPlant] instead of checking them itself.
 */
class FeatureAssertionChecker<out T : Any>(private val subjectPlant: IAssertionPlant<T>) : IAssertionCheckerDelegateFail, IAssertionChecker {

    /**
     * Creates an [IFeatureAssertionGroup] based on the given [assertionVerb], [subject] and [assertions]
     * and add the [IFeatureAssertionGroup] to the [subjectPlant] instead of checking it itself.
     */
    override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
        val featureAssertionGroup = FeatureAssertionGroup(featureName = assertionVerb, subSubject = subject, assertions = ArrayList(assertions))
        subjectPlant.addAssertion(featureAssertionGroup)
    }
}
