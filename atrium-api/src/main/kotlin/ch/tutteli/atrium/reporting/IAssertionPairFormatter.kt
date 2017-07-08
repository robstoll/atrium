package ch.tutteli.atrium.reporting

import ch.tutteli.atrium.reporting.translating.ITranslatable

interface IAssertionPairFormatter {
    fun format(methodObject: AssertionFormatterMethodObject, translatable: ITranslatable, representation: Any)
}
