package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHintBuilder
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHintOption

internal class DescriptiveAssertionWithFailureHintOptionImpl(
    private val test: () -> Boolean,
    private val failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHintOption {

   override val showForAnyFailure get(): DescriptiveAssertionWithFailureHintBuilder
        = DescriptiveAssertionWithFailureHintBuilderImpl(test, { true }, failureHintFactory)

    override fun showOnlyIf(predicate: () -> Boolean): DescriptiveAssertionWithFailureHintBuilder
        = DescriptiveAssertionWithFailureHintBuilderImpl(test, predicate, failureHintFactory)
}
