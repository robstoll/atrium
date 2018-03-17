package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

/**
 * Provides options to create an [ObjectFormatter].
 */
interface ObjectFormatterOption {

    /**
     * The previously chosen [Translator].
     */
    val translator: Translator

    /**
     * Uses [CoreFactory.newDetailedObjectFormatter] as [ObjectFormatter].
     */
    fun withDetailedObjectFormatter(): AssertionFormatterControllerOption

    /**
     * Uses the given [factory] to build a custom [ObjectFormatter].
     */
    fun withObjectFormatter(factory: (Translator) -> ObjectFormatter): AssertionFormatterControllerOption
}

internal class ObjectFormatterOptionImpl(
    override val translator: Translator
) : ObjectFormatterOption {

    override fun withDetailedObjectFormatter()
        = AssertionFormatterControllerOptionImpl(coreFactory.newDetailedObjectFormatter(translator), translator)

    override fun withObjectFormatter(factory: (Translator) -> ObjectFormatter)
        = AssertionFormatterControllerOptionImpl(factory(translator), translator)
}
