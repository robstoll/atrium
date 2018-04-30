package ch.tutteli.atrium.domain.builders.reporting.impl

import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.reporting.ObjectFormatterOption
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

internal class ObjectFormatterOptionImpl(
    override val translator: Translator
) : ObjectFormatterOption {

    override fun withDetailedObjectFormatter()
        = AssertionFormatterControllerOptionImpl(
        coreFactory.newDetailedObjectFormatter(translator), translator
    )

    override fun withObjectFormatter(factory: (Translator) -> ObjectFormatter)
        = AssertionFormatterControllerOptionImpl(
        factory(translator),
        translator
    )
}
