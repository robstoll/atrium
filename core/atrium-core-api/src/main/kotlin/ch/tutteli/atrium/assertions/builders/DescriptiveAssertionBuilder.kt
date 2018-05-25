package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Builder to create a [DescriptiveAssertion].
 */
interface DescriptiveAssertionBuilder : DescriptiveLikeAssertionBuilder {

    /**
     * Creates an [DescriptiveAssertion] based on the given [description], [representation] and
     * the previously defined [test].
     */
    override fun create(description: Translatable, representation: Any): DescriptiveAssertion
}
