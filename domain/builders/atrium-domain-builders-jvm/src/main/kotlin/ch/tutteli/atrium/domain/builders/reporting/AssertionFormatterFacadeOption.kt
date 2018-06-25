package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.AssertionFormatterFacadeOptionImpl
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

    companion object {
        fun create(
            assertionFormatterController: AssertionFormatterController,
            objectFormatter: ObjectFormatter,
            translator: Translator
        ): AssertionFormatterFacadeOption
            = AssertionFormatterFacadeOptionImpl(assertionFormatterController, objectFormatter, translator)
    }
}
