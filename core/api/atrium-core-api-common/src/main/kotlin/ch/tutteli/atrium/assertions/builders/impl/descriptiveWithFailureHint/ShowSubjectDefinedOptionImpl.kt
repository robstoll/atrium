package ch.tutteli.atrium.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionWithFailureHint

internal class ShowSubjectDefinedOptionImpl<T> : DescriptiveAssertionWithFailureHint.ShowSubjectDefinedOption<T> {

    override fun ifDefined(failureHintFactory: (T) -> Boolean): DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption<T> =
        DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption.create(failureHintFactory)
}
