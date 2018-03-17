package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Provides options to create an [AssertionFormatterController].
 */
interface AssertionFormatterControllerOption {

    /**
     * The previously chosen [ObjectFormatter].
     */
    val objectFormatter: ObjectFormatter

    /**
     * The previously chosen [Translator].
     */
    val translator: Translator

    /**
     * Uses [CoreFactory.newAssertionFormatterController] as [AssertionFormatterController].
     */
    fun withDefaultAssertionFormatterController(): AssertionFormatterFacadeOption

    /**
     * Uses the given [assertionFormatterController] a custom [AssertionFormatterController].
     */
    fun withAssertionFormatterController(assertionFormatterController: AssertionFormatterController): AssertionFormatterFacadeOption
}

internal class AssertionFormatterControllerOptionImpl(
    override val objectFormatter: ObjectFormatter,
    override val translator: Translator
) : AssertionFormatterControllerOption {

    override fun withDefaultAssertionFormatterController()
        = AssertionFormatterFacadeOptionImpl(
            coreFactory.newAssertionFormatterController(),
            objectFormatter,
            translator
        )

    override fun withAssertionFormatterController(assertionFormatterController: AssertionFormatterController)
        = AssertionFormatterFacadeOptionImpl(
            assertionFormatterController,
            objectFormatter,
            translator
        )
}
