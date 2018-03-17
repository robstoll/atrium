package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.AssertionFormatterFacade
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Provides options to create an [AssertionFormatterFacade].
 */
interface AssertionFormatterFacadeOption {

    /**
     * The previously chosen [AssertionFormatterController]
     */
    val assertionFormatterController: AssertionFormatterController

    /**
     * The previously chosen [ObjectFormatter]
     */
    val objectFormatter: ObjectFormatter

    /**
     * The previously chosen [Translator]
     */
    val translator: Translator

    /**
     * Uses [CoreFactory.newAssertionFormatterFacade] as [AssertionFormatterFacade].
     */
    fun withDefaultAssertionFormatterFacade(): AssertionPairFormatterOption

    /**
     * Uses the given [factory] to build a custom [AssertionFormatterFacade].
     */
    fun withAssertionFormatterFacade(factory: (AssertionFormatterController) -> AssertionFormatterFacade): AssertionPairFormatterOption
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

internal class AssertionFormatterFacadeOptionImpl(
    override val assertionFormatterController: AssertionFormatterController,
    override val objectFormatter: ObjectFormatter,
    override val translator: Translator
) : AssertionFormatterFacadeOption {

    override fun withDefaultAssertionFormatterFacade()
        = AssertionPairFormatterOptionImpl(AssertionFormatterChosenOptions(
            coreFactory.newAssertionFormatterFacade(assertionFormatterController),
            objectFormatter,
            translator
        ))

    override fun withAssertionFormatterFacade(factory: (AssertionFormatterController) -> AssertionFormatterFacade)
        = AssertionPairFormatterOptionImpl(AssertionFormatterChosenOptions(
            factory(assertionFormatterController), objectFormatter, translator
        ))
}
