package ch.tutteli.atrium.domain.builders.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHint

class FailureHintSubjectAbsentOptionImpl<T>(override val ifDefined: (T) -> Assertion) : DescriptiveAssertionWithFailureHint.FailureHintSubjectAbsentOption<T>
