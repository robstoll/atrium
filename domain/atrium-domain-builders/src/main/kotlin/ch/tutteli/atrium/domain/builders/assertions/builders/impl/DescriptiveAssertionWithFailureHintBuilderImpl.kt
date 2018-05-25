package ch.tutteli.atrium.domain.builders.assertions.builders.impl

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.assertions.composers.assertionComposer
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHintBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

internal class DescriptiveAssertionWithFailureHintBuilderImpl internal constructor(
    override val test: () -> Boolean,
    override val showHint: () -> Boolean,
    override val failureHintFactory: () -> Assertion
) : DescriptiveAssertionWithFailureHintBuilder {

    override fun create(description: Translatable, representation: Any): Assertion
        = assertionComposer.createDescriptiveWithFailureHint(
            description,
            representation,
            test,
            showHint,
            failureHintFactory
        )
}
