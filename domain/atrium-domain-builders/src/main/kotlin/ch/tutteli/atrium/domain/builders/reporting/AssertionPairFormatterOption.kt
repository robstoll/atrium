package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
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

/**
 * Represents the so far chosen options which are relevant to create [AssertionFormatter]s.
 *
 * @param assertionFormatterFacade The previously chosen [AssertionFormatterFacade]
 * @param objectFormatter The previously chosen [ObjectFormatter]
 * @param translator The previously chosen [Translator]
 */
data class AssertionFormatterChosenOptions(
    val assertionFormatterFacade: AssertionFormatterFacade,
    val objectFormatter: ObjectFormatter,
    val translator: Translator
)
