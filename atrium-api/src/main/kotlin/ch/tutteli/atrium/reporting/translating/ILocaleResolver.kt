package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Responsible to determine in which order [Locale]s should be processed.
 */
interface ILocaleResolver {
    fun resolve(locale: Locale, fallbackLocales: Array<out Locale>): Sequence<Locale>
}
