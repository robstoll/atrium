package ch.tutteli.atrium.domain.builders.reporting

import ch.tutteli.atrium.core.CoreFactory
import ch.tutteli.atrium.domain.builders.reporting.impl.ObjectFormatterOptionImpl
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

    companion object {
        fun create(translator: Translator): ObjectFormatterOption = ObjectFormatterOptionImpl(translator)
    }
}
