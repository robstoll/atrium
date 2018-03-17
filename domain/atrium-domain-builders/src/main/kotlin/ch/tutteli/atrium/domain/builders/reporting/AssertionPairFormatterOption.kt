package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionPairFormatter
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Provides options to create an [AssertionPairFormatter].
 */
interface AssertionPairFormatterOption {

    /**
     * The so far chosen options which are relevant to create [AssertionFormatter]s.
     */
    val options: AssertionFormatterChosenOptions

    /**
     * Uses [CoreFactory.newTextSameLineAssertionPairFormatter] as [AssertionPairFormatter].
     */
    fun withTextSameLineAssertionPairFormatter(): TextAssertionFormatterOption

    /**
     * Uses the given [factory] to build a custom [AssertionPairFormatter].
     */
    fun withTextAssertionPairFormatter(factory: (ObjectFormatter, Translator) -> AssertionPairFormatter): TextAssertionFormatterOption

}

internal class AssertionPairFormatterOptionImpl(
    override val options: AssertionFormatterChosenOptions
) : AssertionPairFormatterOption {

    override fun withTextSameLineAssertionPairFormatter()
        = TextAssertionFormatterOptionImpl(
            options,
            coreFactory.newTextSameLineAssertionPairFormatter(options.objectFormatter, options.translator)
        )

    override fun withTextAssertionPairFormatter(factory: (ObjectFormatter, Translator) -> AssertionPairFormatter)
        = TextAssertionFormatterOptionImpl(
            options,
            factory(options.objectFormatter, options.translator)
        )
}
