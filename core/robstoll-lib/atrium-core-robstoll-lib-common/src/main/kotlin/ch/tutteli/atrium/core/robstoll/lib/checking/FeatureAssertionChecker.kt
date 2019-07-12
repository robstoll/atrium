package ch.tutteli.atrium.core.robstoll.lib.checking

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.checking.AssertionChecker
import ch.tutteli.atrium.creating.AssertionHolder
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * An [AssertionChecker] useful for feature assertions. It creates an [AssertionGroup] of [type][AssertionGroup]
 * [FeatureAssertionGroupType], adds the given assertions to it and finally adds the group to the given
 * [originalAssertionHolder].
 *
 * Or in other words, instead of checking the given assertions itself, it wraps them into a feature assertion group
 * and delegates checking of this group (hence including the given assertion) to the [originalAssertionHolder].
 *
 * @property originalAssertionHolder The plant which holds the assertions of the subject of the feature.
 *   For instance, if the feature is `Person::name` then [originalAssertionHolder] holds the assertions for
 *   the corresponding `Person`.
 *
 * @constructor  An [AssertionChecker] useful for feature assertions. It creates an [AssertionGroup] of
 *   [type][AssertionGroup] [FeatureAssertionGroupType], adds the given assertions to it and finally adds the
 * group to the given [originalAssertionHolder].
 * @param originalAssertionHolder The plant which holds the assertions of the subject of the feature.
 *   For instance, if the feature is `Person::name` then [originalAssertionHolder] holds the assertions for
 *   the corresponding `Person`.
 */
class FeatureAssertionChecker(private val originalAssertionHolder: AssertionHolder) : AssertionChecker {

    /**
     * Creates an [AssertionGroup]s of [type][AssertionGroup] [FeatureAssertionGroupType] based on the
     * given [assertionVerb], [representationProvider] and [assertions] and [adds][AssertionHolder.addAssertion] the
     * assertion group to the [originalAssertionHolder] instead of checking it itself.
     *
     * Notice, this method will change signature with 1.0.0, representationProvider will change to `representation: Any`
     *
     * @param assertionVerb Is used as [AssertionGroup.description] -- as side notice,
     *   the parameter was not renamed to `featureName` due to potential issues with named parameters.
     * @param representationProvider Provides the [AssertionGroup.representation]
     * @param assertions Is used as [AssertionGroup.assertions].
     *
     * @throws AssertionError Might throw an [AssertionError] in case one of the given [assertions] does not hold.
     */
    override fun check(assertionVerb: Translatable, representationProvider: () -> Any, assertions: List<Assertion>) {
        originalAssertionHolder.addAssertion(assertionBuilder.feature
            .withDescriptionAndRepresentation(assertionVerb, representationProvider)
            .withAssertions(ArrayList(assertions))
            .build()
        )
    }
}
