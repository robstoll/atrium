package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [Translator] which supports [TranslatableWithArgs].
 *
 * Therefore, it provides a default implementation for [translate] but in turn defines
 * an abstract method [translateWithoutArgs] which sub-classes have to implement.
 *
 * @property primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *           which will be used in [java.lang.String.format], which in turn is used to substitute the placeholders in
 *           the resulting translation of [ITranslatableWithArgs.translatable] with the [ITranslatableWithArgs.arguments].
 * @property fallbackLocales Used in case a translation for a given [Translatable] is not defined for
 *           [primaryLocale] or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
 *
 * @constructor
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *                      which will be used in [java.lang.String.format], which in turn is used to substitute the
 *                      placeholders in the resulting translation of [ITranslatableWithArgs.translatable] with
 *                      the [ITranslatableWithArgs.arguments].
 * @param fallbackLocales Used in case a translation for a given [Translatable] is not defined for
 *        [primaryLocale] or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.

 */
abstract class ArgumentsSupportingTranslator(
    protected val primaryLocale: Locale,
    protected val fallbackLocales: Array<out Locale>
) : Translator {

    init {
        listOf(primaryLocale, *fallbackLocales).forEach {
            require(it.language != "no") {
                "The macrolanguage `no` is not supported but $it given.\nUse either nb_... or nn_..."
            }
            require(it.language != "zh" || it.country.isNotEmpty() || (it.script != "Hans" && it.script != "Hant")) {
                val countries = if (it.script == "Hant") {
                    "TW, HK or MO"
                } else {
                    "CN or SG"
                }
                "Script `${it.script}` for Locale with language `zh` is not supported.\nUse a corresponding country instead ($countries)."
            }
        }
    }

    /**
     * Returns the translation of the given [translatable] or its [getDefault][Translatable.getDefault]
     * in case there is not a translation defined for it.
     *
     * In case of a [TranslatableWithArgs] it translates the [Translatable] of the given [TranslatableWithArgs]
     * and formats it with its [arguments][TranslatableWithArgs.arguments].
     *
     * @param translatable The [Translatable] which shall be translated.
     *
     * @return The result of the translation for the given [translatable].
     */
    override final fun translate(translatable: Translatable): String = when (translatable) {
        is TranslatableWithArgs -> translateWithArgs(translatable)
        else -> translateWithoutArgs(translatable)
    }

    /**
     * Do not call this method in case you want to translate a [TranslatableWithArgs]
     * use [translate] in this case.
     *
     * This method needs to be implemented by sub classes, which have to return the translation for the given
     * [translatable] or the result of [Translatable.getDefault] if there is no translation defined for the given
     * [translatable].
     *
     * @param translatable The [Translatable] which shall be translated.
     *
     * @return The translation for the given [translatable] or
     *         [ITranslatable.getDefault] of the given [translatable] in case there is no translation defined
     */
    protected abstract fun translateWithoutArgs(translatable: Translatable): String

    private fun translateWithArgs(translatableWithArgs: TranslatableWithArgs): String {
        val result = translateWithoutArgs(translatableWithArgs.translatable)
        val arr = Array(translatableWithArgs.arguments.size) { index ->
            val arg = translatableWithArgs.arguments[index]
            when (arg) {
                is Translatable -> translate(arg)
                else -> arg
            }
        }
        return String.format(primaryLocale, result, *arr)
    }
}
