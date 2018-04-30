package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterChosenOptions
import ch.tutteli.atrium.domain.builders.reporting.AssertionPairFormatterOption
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

internal class AssertionPairFormatterOptionImpl(
    override val options: AssertionFormatterChosenOptions
) : AssertionPairFormatterOption {

    override fun withTextSameLineAssertionPairFormatter()
        = TextAssertionFormatterOptionImpl(
        options,
        coreFactory.newTextSameLineAssertionPairFormatter(
            options.objectFormatter,
            options.translator
        )
    )

    override fun withTextAssertionPairFormatter(factory: (ObjectFormatter, Translator) -> AssertionPairFormatter)
        = TextAssertionFormatterOptionImpl(
        options,
        factory(options.objectFormatter, options.translator)
    )
}
