package ch.tutteli.atrium.assertions.builders

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.reporting.translating.Untranslatable

/**
 * Builder to create a [DescriptiveAssertion].
 */
interface DescriptiveAssertionBuilder {

    /**
     * Delegates to the `create` overload with a `test` which returns constantly `true`.
     */
    fun createHoldingAssertion(description: String, representation: Any): DescriptiveAssertion
        = create(description, representation, trueProvider)

    /**
     * Delegates to the `create` overload with a `test` which returns constantly `false`.
     */
    fun createFailingAssertion(description: String, representation: Any): DescriptiveAssertion
        = create(description, representation, falseProvider)

    /**
     * Delegates to the `create` overload with a `test` which returns constantly `true`.
     */
    fun createHoldingAssertion(description: Translatable, representation: Any): DescriptiveAssertion
        = create(description, representation, trueProvider)

    /**
     * Delegates to the `create` overload with a `test` which returns constantly `false`.
     */
    fun createFailingAssertion(description: Translatable, representation: Any): DescriptiveAssertion
        = create(description, representation, falseProvider)


    /**
     * Wraps the given [description] into an [Untranslatable] and delegates to the other `create` overload.
     */
    fun create(description: String, representation: Any, test: () -> Boolean): DescriptiveAssertion
        = create(Untranslatable(description), representation, test)

    /**
     * Creates an [DescriptiveAssertion] based on the given [description], [representation] and [test].
     */
    fun create(description: Translatable, representation: Any, test: () -> Boolean): DescriptiveAssertion

    companion object {
        private val trueProvider = { true }
        private val falseProvider = { false }
    }
}

