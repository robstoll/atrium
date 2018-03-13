package ch.tutteli.atrium.checking

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * An [AssertionChecker] useful for feature assertions. It creates an [AssertionGroup] of [type][AssertionGroup]
 * [FeatureAssertionGroupType], adds the given assertions to it and finally adds the group to the given
 * [subjectPlant].
 *
 * Or in other words, instead of checking the given assertions itself, it wraps them into a feature assertion group
 * and delegates checking of this group (hence including the given assertion) to the [subjectPlant].
 *
 * @param T The type of the [subject][AssertionPlant.subject] of the given [subjectPlant].
 *
 * @property subjectPlant The plant which holds the assertions of the subject of the feature.
 *   For instance, if the feature is `Person::name` then [subjectPlant] holds the assertions for
 *   the corresponding `Person`.
 *
 * @constructor  An [AssertionChecker] useful for feature assertions. It creates an [AssertionGroup] of
 *   [type][AssertionGroup] [FeatureAssertionGroupType], adds the given assertions to it and finally adds the
 * group to the given [subjectPlant].
 * @param subjectPlant The plant which holds the assertions of the subject of the feature.
 *   For instance, if the feature is `Person::name` then [subjectPlant] holds the assertions for
 *   the corresponding `Person`.
 */
class FeatureAssertionChecker<out T : Any>(private val subjectPlant: AssertionPlant<T>) : AssertionChecker {

    /**
     * Creates an [AssertionGroup]s of [type][AssertionGroup] [FeatureAssertionGroupType] based on the
     * given [assertionVerb], [subject] and [assertions] and [adds][AssertionPlant.addAssertion] the
     * assertion group to the [subjectPlant] instead of checking it itself.
     *
     * @param assertionVerb Is used as [AssertionGroup.name] -- as side notice,
     *   the parameter was not renamed to `featureName` due to potential issues with named parameters.
     * @param subject Is used as [AssertionGroup.subject] -- as side notice,
     *   the parameter was not renamed to `feature` due to potential issues with named parameters.
     * @param assertions Is used as [AssertionGroup.assertions].
     *
     * @throws AssertionError Might throw an [AssertionError] in case one of the given [assertions] does not hold.
     */
    override fun check(assertionVerb: Translatable, subject: Any, assertions: List<Assertion>) {
        val featureAssertionGroup = AssertionBuilder.feature.create(assertionVerb, subject, ArrayList(assertions))
        subjectPlant.addAssertion(featureAssertionGroup)
    }
}
