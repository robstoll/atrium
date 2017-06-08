package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * This translator does not translate but uses [ITranslatable.getDefault] instead
 * and uses [Locale.getDefault] as [primaryLocale] if not defined via constructor parameter.
 */
class UsingDefaultTranslator(primaryLocale: Locale = Locale.getDefault()) : ArgumentsSupportingTranslator(primaryLocale) {
    override fun translateWithoutArgs(translatable: ITranslatable) = translatable.getDefault()
}
