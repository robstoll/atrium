package ch.tutteli.atrium.domain.builders.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.assertions.composers.assertionComposer
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHint
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FinalStepImpl(
    override val test: () -> Boolean,
    override val showHint: () -> Boolean,
    override val failureHintFactory: () -> Assertion,
    override val description: Translatable,
    override val representation: Any
) : DescriptiveAssertionWithFailureHint.FinalStep {

    override fun build(): Assertion = assertionComposer.createDescriptiveWithFailureHint(
        description,
        representation,
        test,
        showHint,
        failureHintFactory
    )
}
