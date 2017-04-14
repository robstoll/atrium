package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.FeatureAssertionGroup
import ch.tutteli.atrium.assertions.IAssertion
import ch.tutteli.atrium.assertions.IFeatureAssertionGroup
import ch.tutteli.atrium.creating.IAssertionPlant

/**
 * An [IAssertionChecker] useful for feature assertions. It creates [IFeatureAssertionGroup]s
 * and adds them to the given [subjectPlant] instead of checking them itself.
 *
 * @property subjectPlant The plant which holds the assertions of the subject of the feature.
 *           For instance, if the feature is `Person::name` then [subjectPlant] holds the assertions for
 *           the corresponding `Person`.
 *
 * @constructor
 * @param subjectPlant The plant which holds the assertions of the subject of the feature.
 *           For instance, if the feature is `Person::name` then [subjectPlant] holds the assertions for
 *           the corresponding `Person`.
 */
internal class FeatureAssertionChecker<out T : Any>(private val subjectPlant: IAssertionPlant<T>) : IAssertionCheckerDelegateFail, IAssertionChecker {

    /**
     * Creates an [IFeatureAssertionGroup] based on the given [assertionVerb], [subject] and [assertions] and
     * [adds][IAssertionPlant.addAssertion] the [IFeatureAssertionGroup] to the [subjectPlant]
     * instead of checking it itself.
     *
     * @param assertionVerb I used as [IFeatureAssertionGroup.featureName] -- as side notice,
     *        the parameter was not renamed to `featureName` due to potential issues with named parameters.
     * @param subject Is used as [IFeatureAssertionGroup.feature] -- as side notice,
     *        the parameter was not renamed to `feature` due to potential issues with named parameters.
     * @param assertions Is used as [IFeatureAssertionGroup.assertions].
     *
     * @throws AssertionError In case one of the given [assertions] does not hold.
     */
    override fun check(assertionVerb: String, subject: Any, assertions: List<IAssertion>) {
        val featureAssertionGroup = FeatureAssertionGroup(assertionVerb, subject, ArrayList(assertions))
        subjectPlant.addAssertion(featureAssertionGroup)
    }
}
