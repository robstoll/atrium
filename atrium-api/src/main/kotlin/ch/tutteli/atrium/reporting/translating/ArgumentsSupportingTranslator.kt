package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Represents an [ITranslator] which supports [ITranslatableWithArgs].
 *
 * Therefore, it provides a default implementation for [translate] but in turn defines
 * an abstract method [translateWithoutArgs] which sub-classes have to implement.
 *
 * @property primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *                         which will be used in [java.lang.String.format], which in turn is used to substitute the
 *                         placeholders in the resulting translation of [ITranslatableWithArgs.translatable] with
 *                         the [ITranslatableWithArgs.arguments].
 *
 * @constructor
 * @param primaryLocale The [Locale] to which the translator translates per default as well as the [Locale]
 *                      which will be used in [java.lang.String.format], which in turn is used to substitute the
 *                      placeholders in the resulting translation of [ITranslatableWithArgs.translatable] with
 *                      the [ITranslatableWithArgs.arguments].
 */
abstract class ArgumentsSupportingTranslator(protected val primaryLocale: Locale) : ITranslator {

    /**
     * Returns the translation of the given [translatable] or its [getDefault][ITranslatable.getDefault]
     * in case there is not a translation defined for it.
     *
     * In case of a [ITranslatableWithArgs] it translates the [ITranslatable] of the given [ITranslatableWithArgs]
     * and formats it with its [arguments][ITranslatableWithArgs.arguments].
     *
     * @return The result of the translation for the given [translatable].
     */
    override final fun translate(translatable: ITranslatable): String = when (translatable) {
        is ITranslatableWithArgs -> translateWithArgs(translatable)
        else -> translateWithoutArgs(translatable)
    }

    /**
     * Do not call this method in case you want to translate a [ITranslatableWithArgs]
     * use [translate] in this case.
     */
    protected abstract fun translateWithoutArgs(translatable: ITranslatable): String

    private fun translateWithArgs(translatableWithArgs: ITranslatableWithArgs): String {
        val result = translateWithoutArgs(translatableWithArgs.translatable)
        return String.format(primaryLocale, result, *translatableWithArgs.arguments)
    }
}
