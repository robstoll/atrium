package ch.tutteli.atrium.assertions.assertionbuilder

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionBuilder
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.creating.PlantHasNoSubjectException
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Builder to create a descriptive [Assertion] with an additional hint which is only shown if the `test()` fails.
 */
@Suppress("unused")
fun AssertionBuilder.DescriptiveAssertionBuilder.withFailureHint(failureHintFactory: () -> AssertionGroup)
    = DescriptiveAssertionWithFailureHintOption(failureHintFactory)

/**
 * Provides options to create a descriptive [Assertion] with an additional failure hint.
 */
class DescriptiveAssertionWithFailureHintOption(private val failureHintFactory: () -> AssertionGroup) {
    /**
     * Specifies that the failure hint shall be shown in any case.
     */
    val showForAnyFailure get() = DescriptiveAssertionWithFailureHintBuilder({ true }, failureHintFactory)

    /**
     * Specifies that the failure hint shall only be shown if the given [predicate] holds
     */
    fun showOnlyIf(predicate: () -> Boolean) = DescriptiveAssertionWithFailureHintBuilder(predicate, failureHintFactory)
}

class DescriptiveAssertionWithFailureHintBuilder internal constructor(
    private val showHint: () -> Boolean,
    private val failureHintFactory: () -> AssertionGroup
) {
    fun create(
        description: Translatable,
        representation: Any,
        test: () -> Boolean
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
