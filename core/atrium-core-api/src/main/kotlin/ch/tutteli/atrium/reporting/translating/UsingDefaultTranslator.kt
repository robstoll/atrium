package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * This translator does not translate but uses [Translatable.getDefault] instead
 * and uses [Locale.getDefault] as [primaryLocale] if not defined differently via constructor parameter.
 *
 * @constructor This translator does not translate but uses [Translatable.getDefault] instead.
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *   which will be used in [java.lang.String.format], which in turn is used to substitute the placeholders in
 *   the resulting translation of [TranslatableWithArgs.translatable] with the [TranslatableWithArgs.arguments].
 *   If not defined at all, it will use [Locale.getDefault] as fallback.
 */
class UsingDefaultTranslator(
    primaryLocale: Locale = Locale.getDefault()
) : ArgumentsSupportingTranslator(primaryLocale, arrayOf()) {

    override fun translateWithoutArgs(translatable: Translatable) = translatable.getDefault()
}
