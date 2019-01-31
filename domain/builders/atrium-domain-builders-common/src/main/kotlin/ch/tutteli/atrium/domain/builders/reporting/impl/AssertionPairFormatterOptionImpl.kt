package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterChosenOptions
import ch.tutteli.atrium.domain.builders.reporting.AssertionPairFormatterOption
import ch.tutteli.atrium.domain.builders.reporting.TextAssertionFormatterOption
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

internal class AssertionPairFormatterOptionImpl(
    override val options: AssertionFormatterChosenOptions
) : AssertionPairFormatterOption {

    override fun withTextAssertionPairFormatter(factory: (ObjectFormatter, Translator) -> AssertionPairFormatter)
        = TextAssertionFormatterOption.create(options, factory(options.objectFormatter, options.translator))
}
