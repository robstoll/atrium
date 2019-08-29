package ch.tutteli.atrium.reporting.translating

/**
 * This translator does not translate but uses [Translatable.getDefault] instead
 * and uses [getDefaultLocale] as [primaryLocale] if not defined differently via constructor parameter.
 *
 * @constructor This translator does not translate but uses [Translatable.getDefault] instead.
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *   which will be used in [String.format], which in turn is used to substitute the placeholders in
 *   the resulting translation of [TranslatableWithArgs.translatable] with the [TranslatableWithArgs.arguments].
 *   If not defined at all, it will use [getDefaultLocale] as fallback.
 */
class UsingDefaultTranslator(
    primaryLocale: Locale = getDefaultLocale()
) : ArgumentsSupportingTranslator(primaryLocale, listOf()) {

    override fun translateWithoutArgs(translatable: Translatable) = translatable.getDefault()
}
