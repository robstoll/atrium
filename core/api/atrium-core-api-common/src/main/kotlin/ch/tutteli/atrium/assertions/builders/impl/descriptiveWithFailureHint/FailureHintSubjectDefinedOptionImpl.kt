package ch.tutteli.atrium.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionWithFailureHint

internal class FailureHintSubjectDefinedOptionImpl<T> : DescriptiveAssertionWithFailureHint.FailureHintSubjectDefinedOption<T> {

    override fun ifDefined(failureHintFactory: (T) -> Assertion): DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption<T> =
        DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption.create(failureHintFactory)
}
