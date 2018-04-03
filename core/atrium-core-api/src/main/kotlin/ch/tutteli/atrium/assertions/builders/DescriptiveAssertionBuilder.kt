package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Builder to create a [DescriptiveAssertion].
 */
interface DescriptiveAssertionBuilder {

    /**
     * Wraps the [test] into a lambda and delegates to the other `create` overload.
     */
    fun create(description: String, representation: Any, test: Boolean): DescriptiveAssertion
        = create(description, representation, { test })

    /**
     * Wraps the given [description] into an [Untranslatable] and delegates to the other `create` overload.
     */
    fun create(description: String, representation: Any, test: () -> Boolean): DescriptiveAssertion
        = create(Untranslatable(description), representation, test)

    /**
     * Creates an [DescriptiveAssertion] based on the given [description], [representation] and [test].
     */
    fun create(description: Translatable, representation: Any, test: Boolean): DescriptiveAssertion
        = create(description, representation, { test })

    /**
     * Creates an [DescriptiveAssertion] based on the given [description], [representation] and [test].
     */
    fun create(description: Translatable, representation: Any, test: () -> Boolean): DescriptiveAssertion
}

