package ch.tutteli.atrium.reporting.translating


/**
 * Responsible to decide in which order [Locale]s should be processed.
 *
 * It should follow Java's Locale conventions (e.g. locales are written with `_` and not with `-`). To put it
 * differently it should be more or less compatible with Java's `ResourceBundle.Control.getCandidateLocales` except for:
 * - special case Norwegian; language `no` does not need to be considered, is not supported by
 *   [Translator].
 * - special case Chinese; language `zh` with script `Hant` or `Hans` without providing a country does not need to
 *   be treated specially because [Translator] does not support it. However, it still has to set script to `Hant`
 *   or `Hans` in case script is not defined by the user but country was.
 * - `java.util.Locale.ROOT` does not exist for in Atrium's [Locale].
 */
@Deprecated("will be removed with 2.0.0 at the latest without replacement")
interface LocaleOrderDecider {

    /**
     * Defines the [Sequence] of [Locale]s which should be used in [Translator.translate].
     *
     * The first [Locale] of the [Sequence] is the given [primaryLocale], then secondary alternatives follow
     * as defined by Java's `ResourceBundle.Control.getCandidateLocales`  and finally the given [fallbackLocales]
     * (in the given order) are used as fallback for the [primaryLocale], which means the process starts over again.
     *
     * @param primaryLocale The primary [Locale] which should be the first element of the returned [Sequence].
     * @param fallbackLocales The fallback [Locale]'s which should be used in case no translation could be found for
     *   the [primaryLocale] and its secondary alternatives.
     *
     * @return A [Sequence] of [Locale]s which defines the order in which translations should be searched for.
     */
    @Deprecated("interface will be removed with 2.0.0 at the latest without replacement")
    fun determineOrder(primaryLocale: Locale, fallbackLocales: List<Locale>): Sequence<Locale>
}

