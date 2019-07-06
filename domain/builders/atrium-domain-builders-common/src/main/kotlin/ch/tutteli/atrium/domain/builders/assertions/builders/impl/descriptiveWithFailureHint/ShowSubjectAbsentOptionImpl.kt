package ch.tutteli.atrium.domain.builders.assertions.builders.impl.descriptiveWithFailureHint

import ch.tutteli.atrium.domain.builders.assertions.builders.DescriptiveAssertionWithFailureHint

class ShowSubjectAbsentOptionImpl<T>(override val ifDefined: (T) -> Boolean) : DescriptiveAssertionWithFailureHint.ShowSubjectAbsentOption<T>
