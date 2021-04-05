package ch.tutteli.atrium.assertions


/**
 * Represents the [AssertionGroupType] for [AssertionGroup]s which contain some kind of feature assertions.
 */
interface FeatureAssertionGroupType : AssertionGroupType

/**
 * This class is only used as [BulletPointIdentifier].
 */
class PrefixFeatureAssertionGroupHeader private constructor() : BulletPointIdentifier

/**
 * The [AssertionGroupType] for [AssertionGroup]s which contain any kind of feature assertions.
 */
object DefaultFeatureAssertionGroupType : FeatureAssertionGroupType
