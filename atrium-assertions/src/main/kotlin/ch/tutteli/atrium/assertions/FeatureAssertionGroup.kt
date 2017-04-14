package ch.tutteli.atrium.assertions

/**
 * Represent a group of [IAssertion] identified by a [featureName] and a belonging [subSubject].
 *
 * @constructor
 * @param featureName The name of the feature.
 * @param featureName The subject of this feature, for which the [assertions] are defined for.
 * @param assertions The assertions of this group, which are defined for [subSubject].
 */
data class FeatureAssertionGroup(
    override val featureName: String,
    override val subSubject: Any,
    override val assertions: List<IAssertion>) : IFeatureAssertionGroup
