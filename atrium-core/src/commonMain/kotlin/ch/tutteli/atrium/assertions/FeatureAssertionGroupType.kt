//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions


/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s which contain some kind of feature assertions.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
interface FeatureAssertionGroupType : AssertionGroupType

/**
 * This class is only used as [BulletPointIdentifier].
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
class PrefixFeatureAssertionGroupHeader private constructor() : BulletPointIdentifier

/**
 * The [AssertionGroupType] for [AssertionGroup]s which contain any kind of feature assertions.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
object DefaultFeatureAssertionGroupType : FeatureAssertionGroupType
