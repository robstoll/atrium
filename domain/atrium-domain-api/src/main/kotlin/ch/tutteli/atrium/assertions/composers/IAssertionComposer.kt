package ch.tutteli.atrium.assertions.composers

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.AssertionBuilder
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * Responsible to compose [Assertion]s based on the [AssertionBuilder].
 */
interface IAssertionComposer {
    /**
     * Shall create kind of a [DescriptiveAssertion] if [test] holds or an [AssertionGroup] which includes additionally
     * a failure hint created by the given [failureHintFactory] in case [showHint] evaluates to `true`.
     *
     * Notice, it is up to the implementation if a really a [DescriptiveAssertion] is created if [test] holds or if
     * another kind of [Assertion] is used. However, the implementation should treat the given [description] and
     * [representation] as it would be treated if it is part of a [DescriptiveAssertion].
     *
     * @param description The [DescriptiveAssertion.description].
     * @param representation The [DescriptiveAssertion.representation].
     * @param test The test which should be used for [DescriptiveAssertion.holds].
     * @param showHint Indicates whether the failure hint shall be shown in case [test] fails.
     * @param failureHintFactory A factory method used to create the failure hint.
     */
    fun createDescriptiveWithFailureHint(
        description: Translatable,
        representation: Any,
        test: () -> Boolean,
        showHint: () -> Boolean,
        failureHintFactory: () -> Assertion
    ): Assertion
}
