package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.reporting.translating.ITranslatable

/**
 * An [IAssertionChecker] useful for feature assertions. It creates an [IAssertionGroup] of [type][IAssertionGroup]
 * [IFeatureAssertionGroupType], adds the given assertions to it and finally adds the group to the given
 * [subjectPlant].
 *
 * Or in other words, instead of checking the given assertions itself, it wraps them into a feature assertion group
 * and delegates checking of this group (hence including the given assertion) to the [subjectPlant].
 *
 * @param T The type of the [subject][IAssertionPlant.subject] of the given [subjectPlant].
 *
 * @property subjectPlant The plant which holds the assertions of the subject of the feature.
 *           For instance, if the feature is `Person::name` then [subjectPlant] holds the assertions for
 *           the corresponding `Person`.
 *
 * @constructor  An [IAssertionChecker] useful for feature assertions. It creates an [IAssertionGroup] of
 * [type][IAssertionGroup] [IFeatureAssertionGroupType], adds the given assertions to it and finally adds the
 * group to the given [subjectPlant].
 * @param subjectPlant The plant which holds the assertions of the subject of the feature.
 *           For instance, if the feature is `Person::name` then [subjectPlant] holds the assertions for
 *           the corresponding `Person`.
 */
class FeatureAssertionChecker<out T : Any>(private val subjectPlant: IAssertionPlant<T>) : IAssertionChecker {

    /**
     * Creates an [IAssertionGroup]s of [type][IAssertionGroup] [IFeatureAssertionGroupType] based on the
     * given [assertionVerb], [subject] and [assertions] and [adds][IAssertionPlant.addAssertion] the
     * assertion group to the [subjectPlant] instead of checking it itself.
     *
     * @param assertionVerb Is used as [IAssertionGroup.name] -- as side notice,
     *        the parameter was not renamed to `featureName` due to potential issues with named parameters.
     * @param subject Is used as [IAssertionGroup.subject] -- as side notice,
     *        the parameter was not renamed to `feature` due to potential issues with named parameters.
     * @param assertions Is used as [IAssertionGroup.assertions].
     *
     * @throws AssertionError Might throw an [AssertionError] in case one of the given [assertions] does not hold.
     */
    override fun check(assertionVerb: ITranslatable, subject: Any, assertions: List<IAssertion>) {
        val featureAssertionGroup = AssertionGroup(FeatureAssertionGroupType, assertionVerb, subject, ArrayList(assertions))
        subjectPlant.addAssertion(featureAssertionGroup)
    }
}
