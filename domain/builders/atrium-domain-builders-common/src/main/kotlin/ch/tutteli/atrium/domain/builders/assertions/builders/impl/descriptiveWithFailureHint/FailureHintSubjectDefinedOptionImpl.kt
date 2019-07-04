package ch.tutteli.atrium.domain.builders.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHint

class FailureHintSubjectDefinedOptionImpl<T> : DescriptiveAssertionWithFailureHint.FailureHintSubjectDefinedOption<T> {

    override fun ifDefined(failureHintFactory: (T) -> Assertion): DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption<T> =
        DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption.create(failureHintFactory)
}
