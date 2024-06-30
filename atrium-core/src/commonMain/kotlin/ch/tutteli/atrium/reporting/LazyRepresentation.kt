//TODO 1.3.0 remove suppress if we keep it and switch to ProofGroup etc.
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.FeatureAssertionGroupType

/**
 * A representation which shall be [eval]uated lazily during reporting.
 *
 * For instance, an [AssertionGroup] with a [FeatureAssertionGroupType] has typically a
 * [AssertionGroup.representation] with a [LazyRepresentation].
 */
//TODO 1.3.0 deprecate?
class LazyRepresentation(private val provider: () -> Any?) {

    /**
     * Evaluates the representation.
     */
    fun eval(): Any? = provider()
}
