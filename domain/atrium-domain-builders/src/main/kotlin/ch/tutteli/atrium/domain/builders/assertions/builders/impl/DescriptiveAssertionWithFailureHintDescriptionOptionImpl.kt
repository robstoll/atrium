package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.assertions.composers.assertionComposer
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHintFinalStep
import ch.tutteli.atrium.reporting.translating.Translatable

internal class DescriptiveAssertionWithFailureHintFinalStepImpl (
    override val test: () -> Boolean,
    override val showHint: () -> Boolean,
    override val failureHintFactory: () -> Assertion,
    override val description: Translatable,
    override val representation: Any
) : DescriptiveAssertionWithFailureHintFinalStep {

    override fun build(): Assertion
        = assertionComposer.createDescriptiveWithFailureHint(
            description,
            representation,
            test,
            showHint,
            failureHintFactory
        )
}
