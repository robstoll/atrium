package ch.tutteli.atrium.reporting.translating

import java.util.*

/**
 * Responsible to decide in which order [Locale]s should be processed.
 */
interface ILocaleOrderDecider {
    fun determineOrder(locale: Locale, fallbackLocales: Array<out Locale>): Sequence<Locale>
}

