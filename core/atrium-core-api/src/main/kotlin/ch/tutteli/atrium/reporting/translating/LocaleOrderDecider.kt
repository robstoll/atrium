package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.IAtriumFactory
import java.util.*

/**
 * Responsible to decide in which order [Locale]s should be processed.
 *
 * It has to be compatible with [ResourceBundle.Control.getCandidateLocales] except for:
 * - special case Norwegian; language `no` does not need to be considered, is not supported by
 *   [Translator] (see [IAtriumFactory.newTranslator] for more information).
 * - special case Chinese; language `zh` with script `Hant` or `Hans` without providing a country does not need to
 *   be treated specially because [Translator] does not support it. However, it still has to set script to `Hant`
 *   or `Hans` in case script is not defined by the user but country was.
 * - [Locale.ROOT] which should not be a candidate at all.
 */
interface LocaleOrderDecider {
    /**
     * Defines the [Sequence] of [Locale]s which should be used in [Translator.translate].
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

