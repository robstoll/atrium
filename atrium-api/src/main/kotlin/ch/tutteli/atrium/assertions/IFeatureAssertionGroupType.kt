package ch.tutteli.atrium.assertions

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s which contain some kind of feature assertions.
 */
interface IFeatureAssertionGroupType : IAssertionGroupType

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s which contain any kind of feature assertions.
 */
object FeatureAssertionGroupType: IFeatureAssertionGroupType
