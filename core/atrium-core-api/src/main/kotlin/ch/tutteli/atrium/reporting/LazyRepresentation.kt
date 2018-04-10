package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType


/**
 * A representation which shall be [eval]uated lazily during reporting.
 *
 * For instance, an [AssertionGroup] with a [FeatureAssertionGroupType] has typically a [AssertionGroup.subject] with
 * a [LazyRepresentation].
 */
class LazyRepresentation(private val provider: () -> Any) {
    /**
     * Evaluates the representation
     */
    fun eval() = provider()
}
