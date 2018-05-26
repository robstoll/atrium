package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Base interface for builders which create a descriptive like assertion (such as [DescriptiveAssertion]).
 */
interface DescriptiveLikeAssertionBuilder {
    /**
     * The previously defined test which is used to determine [DescriptiveAssertion.holds].
     */
    val test: () -> Boolean

    /**
     * Wraps the given [description] into an [Untranslatable] and delegates to the other `create` overload.
     */
    fun create(description: String, representation: Any): Assertion
        = create(Untranslatable(description), representation)

    /**
     * Creates an [DescriptiveAssertion] like assertion based on the given [description], [representation] and
     * the previously defined [test].
     */
    fun create(description: Translatable, representation: Any): Assertion
}
