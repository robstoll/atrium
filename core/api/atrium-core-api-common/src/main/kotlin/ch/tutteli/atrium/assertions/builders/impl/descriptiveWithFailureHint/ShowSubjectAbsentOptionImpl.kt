package ch.tutteli.atrium.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionWithFailureHint

internal class ShowSubjectAbsentOptionImpl<T>(
    override val ifDefined: (T) -> Boolean
) : DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption<T>
