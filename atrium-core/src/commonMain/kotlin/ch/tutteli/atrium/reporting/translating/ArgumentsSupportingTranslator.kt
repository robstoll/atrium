package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.kbox.forElementAndForEachIn

/**
 * Represents a [Translator] which supports [TranslatableWithArgs].
 *
 * Therefore, it provides a default implementation for [translate] but in turn defines
 * an abstract method [translateWithoutArgs] which sub-classes have to implement.
 *
 * @property primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *   which will be used in [String.format], which in turn is used to substitute the placeholders in
 *   the resulting translation of [TranslatableWithArgs.translatable] with the [TranslatableWithArgs.arguments].
 * @property fallbackLocales Used in case a translation for a given [Translatable] is not defined for
 *   [primaryLocale] or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
 *
 * @constructor
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *   which will be used in [String.format], which in turn is used to substitute the
 *   placeholders in the resulting translation of [TranslatableWithArgs.translatable] with
 *   the [TranslatableWithArgs.arguments].
 * @param fallbackLocales Used in case a translation for a given [Translatable] is not defined for
 *   [primaryLocale] or one of its secondary alternatives -- the fallback [Locale]s are used in the given order.
 */
abstract class ArgumentsSupportingTranslator(
    protected val primaryLocale: Locale,
    protected val fallbackLocales: List<Locale>
) : Translator {

    init {
        forElementAndForEachIn(primaryLocale, fallbackLocales) {
            require(it.language != "no") {
                "The macrolanguage `no` is not supported but $it given.\nUse either nb_... or nn_..."
            }
            require(it.language != "zh" || it.country != null || (it.script != "Hans" && it.script != "Hant")) {
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
    final override fun translate(translatable: Translatable): String = when (translatable) {
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
     *   [Translatable.getDefault] of the given [translatable] in case there is no translation defined.
     */
    protected abstract fun translateWithoutArgs(translatable: Translatable): String

    private fun translateWithArgs(translatableWithArgs: TranslatableWithArgs): String {
        val result = translateWithoutArgs(translatableWithArgs.translatable)
        val arr = Array(translatableWithArgs.arguments.size) { index ->
            when (val arg = translatableWithArgs.arguments[index]) {
                is Translatable -> translate(arg)
                else -> arg
            }
        }
        return result.format(primaryLocale, arr[0], *arr.drop(1).toTypedArray())
    }
}
