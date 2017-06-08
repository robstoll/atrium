package ch.tutteli.atrium.reporting.translating

import java.util.*

abstract class ArgumentsSupportingTranslator(protected val primaryLocale: Locale) : ITranslator {

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
