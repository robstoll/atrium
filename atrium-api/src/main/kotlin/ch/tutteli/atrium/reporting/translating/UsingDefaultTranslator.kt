package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * This translator does not translate but uses [ITranslatable.getDefault] instead
 * and uses [Locale.getDefault] as [primaryLocale] if not defined differently via constructor parameter.
 *
 * @constructor
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *                      which will be used in [java.lang.String.format], which in turn is used to substitute the
 *                      placeholders in the resulting translation of [ITranslatableWithArgs.translatable] with
 *                      the [ITranslatableWithArgs.arguments].
 *                      If not defined at all, it will use [Locale.getDefault] as fallback.
 */
class UsingDefaultTranslator(primaryLocale: Locale = Locale.getDefault()) : ArgumentsSupportingTranslator(primaryLocale) {
    override fun translateWithoutArgs(translatable: ITranslatable) = translatable.getDefault()
}
