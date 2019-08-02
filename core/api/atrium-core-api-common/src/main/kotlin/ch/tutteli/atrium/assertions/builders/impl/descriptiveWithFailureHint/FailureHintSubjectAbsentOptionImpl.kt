package ch.tutteli.atrium.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.assertions.builders.DescriptiveAssertionWithFailureHint

internal class FailureHintSubjectAbsentOptionImpl<T>(
    override val ifDefined: (T) -> Assertion
) : DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption<T>
