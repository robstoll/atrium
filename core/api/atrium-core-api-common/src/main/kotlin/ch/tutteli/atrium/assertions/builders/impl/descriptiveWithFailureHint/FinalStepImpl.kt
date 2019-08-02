package ch.tutteli.atrium.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionWithFailureHint
import ch.tutteli.atrium.assertions.builders.assertionBuilder
import ch.tutteli.atrium.assertions.builders.fixedClaimGroup
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.reporting.translating.Translatable

internal class FinalStepImpl(
    override val test: () -> Boolean,
    override val showHint: () -> Boolean,
    override val failureHintFactory: () -> Assertion,
    override val description: Translatable,
    override val representation: Any
) : DescriptiveAssertionWithFailureHint.FinalStep {

    override fun build(): Assertion {
        //TODO remove try catch with 1.0.0, should no longer be necessary
        val holds = try {
            test()
        } catch (e: PlantHasNoSubjectException) {
            // failure hint does not need to be shown if plant is absent
            false
        }
        return if (holds || !showHint()) {
            assertionBuilder.createDescriptive(description, representation) { holds }
        } else {
            assertionBuilder.fixedClaimGroup
                .withListType
                .failing
                .withDescriptionAndRepresentation(description, representation)
                .withAssertion(failureHintFactory())
                .build()
        }
    }
}
