package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.AssertionPairFormatterOptionImpl
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
    fun withTextSameLineAssertionPairFormatter(): TextAssertionFormatterOption =
        withTextAssertionPairFormatter(coreFactory::newTextSameLineAssertionPairFormatter)

    /**
     * Uses [CoreFactory.newTextNextLineAssertionPairFormatter] as [AssertionPairFormatter].
     */
    fun withTextNextLineAssertionPairFormatter(): TextAssertionFormatterOption =
        withTextAssertionPairFormatter(coreFactory::newTextNextLineAssertionPairFormatter)

    /**
     * Uses the given [factory] to build a custom [AssertionPairFormatter].
     */
    fun withTextAssertionPairFormatter(factory: (ObjectFormatter, Translator) -> AssertionPairFormatter): TextAssertionFormatterOption

    companion object {
        fun create(
            assertionFormatterFacade: AssertionFormatterFacade,
            objectFormatter: ObjectFormatter,
            translator: Translator
        ): AssertionPairFormatterOption = create(
            AssertionFormatterChosenOptions(assertionFormatterFacade, objectFormatter, translator)
        )

        fun create(options: AssertionFormatterChosenOptions): AssertionPairFormatterOption =
            AssertionPairFormatterOptionImpl(options)
    }
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
