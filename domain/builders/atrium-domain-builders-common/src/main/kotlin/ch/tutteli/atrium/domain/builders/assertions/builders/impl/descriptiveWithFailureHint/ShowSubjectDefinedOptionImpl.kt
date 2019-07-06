package ch.tutteli.atrium.domain.builders.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHint

class ShowSubjectDefinedOptionImpl<T> : DescriptiveAssertionWithFailureHint.ShowSubjectDefinedOption<T> {

    override fun ifDefined(failureHintFactory: (T) -> Boolean): DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption<T> =
        DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption.create(failureHintFactory)
}
