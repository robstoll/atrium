package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Responsible to decide in which order [Locale]s should be processed.
 */
interface ILocaleOrderDecider {
    /**
     * Defines the [Sequence] of [Locale]s which should be used in [ITranslator.translate].
     *
     * The first [Locale] of the [Sequence] is the given [primaryLocale], then secondary alternatives follow
     * as defined by [ResourceBundle.Control.getCandidateLocales] and finally the given [fallbackLocales]
     * (in the given order) are used as fallback for the [primaryLocale], which means the process starts over again.
     *
     * @param primaryLocale The primary [Locale] which should be the first element of the returned [Sequence].
     * @param fallbackLocales The fallback [Locale]'s which should be used in case no translation could be found for
     *                        the [primaryLocale] and its secondary alternatives.
     *
     * @return A [Sequence] of [Locale]s which defines the order in which translations should be searched for.
     */
    fun determineOrder(primaryLocale: Locale, fallbackLocales: Array<out Locale>): Sequence<Locale>
}

