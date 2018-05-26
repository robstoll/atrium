package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHintBuilder
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHintShowOption

internal class DescriptiveAssertionWithFailureHintShowOptionImpl(
    private val test: () -> Boolean,
    private val failureHintFactory: () -> Assertion
): DescriptiveAssertionWithFailureHintShowOption {

   override val showForAnyFailure get(): DescriptiveAssertionWithFailureHintBuilder
        = DescriptiveAssertionWithFailureHintBuilder.create(test, { true }, failureHintFactory)

    override fun showOnlyIf(predicate: () -> Boolean): DescriptiveAssertionWithFailureHintBuilder
        = DescriptiveAssertionWithFailureHintBuilder.create(test, predicate, failureHintFactory)
}
