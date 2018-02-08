package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.assertions.builders.fixHoldsGroup
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Robstoll's implementation of [IAssertionComposer].
 */
object AssertionComposer : IAssertionComposer {

    override fun createDescriptiveWithFailureHint(
        description: Translatable,
        representation: Any,
        test: () -> Boolean,
        showHint: () -> Boolean,
        failureHintFactory: () -> AssertionGroup
    ): Assertion {
        val holds = try {
            test()
        } catch (e: PlantHasNoSubjectException) {
            true //TODO that's a hack, do we have a better solution?
        }
        return if (holds || !showHint()) {
            AssertionBuilder.descriptive.create(description, representation, holds)
        } else {
            AssertionBuilder.fixHoldsGroup.createFailingWithListType(
                description, representation, failureHintFactory()
            )
        }
    }

}
