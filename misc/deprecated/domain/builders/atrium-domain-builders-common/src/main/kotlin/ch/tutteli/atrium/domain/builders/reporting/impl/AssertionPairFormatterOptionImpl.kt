//TODO remove file with 0.17.0
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.domain.builders.reporting.AssertionFormatterChosenOptions
import ch.tutteli.atrium.domain.builders.reporting.AssertionPairFormatterOption
import ch.tutteli.atrium.domain.builders.reporting.TextAssertionFormatterOption
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

@Deprecated("Will be removed with 0.17.0")
internal class AssertionPairFormatterOptionImpl(
    override val options: AssertionFormatterChosenOptions
) : AssertionPairFormatterOption {

    override fun withTextAssertionPairFormatter(factory: (ObjectFormatter, Translator) -> AssertionPairFormatter) =
        TextAssertionFormatterOption.create(options, factory(options.objectFormatter, options.translator))
}
