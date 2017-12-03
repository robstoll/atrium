package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.IAtriumFactory

/**
 * Represents the [IAssertionGroupType] for [IAssertionGroup]s which contain some kind of feature assertions.
 */
interface IFeatureAssertionGroupType : IAssertionGroupType

/**
 * This class is only used as identifier for [IAtriumFactory.registerSameLineTextAssertionFormatterCapabilities].
 */
class PrefixFeatureAssertionGroupHeader private constructor() : IBulletPointIdentifier

/**
 * The [IAssertionGroupType] for [IAssertionGroup]s which contain any kind of feature assertions.
 */
object FeatureAssertionGroupType : IFeatureAssertionGroupType
