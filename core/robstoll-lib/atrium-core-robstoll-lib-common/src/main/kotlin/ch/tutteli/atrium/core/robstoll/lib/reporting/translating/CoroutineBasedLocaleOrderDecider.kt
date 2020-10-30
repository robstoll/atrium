@file:Suppress("EXPERIMENTAL_FEATURE_WARNING", "DEPRECATION")

package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.atrium.reporting.translating.getDefaultLocale
import ch.tutteli.kbox.forElementAndForEachIn

/**
 * Responsible to determine in which order [Locale]s should be processed.
 *
 * Adopted from  Java's `ResourceBundle.Control.getCandidateLocales()` and `ResourceBundle.Control.getFallbackLocale()`
 * but there are a few differences:
 * - is based on Atrium's [Locale] and not `java.util.java`
 * - allows to define more than one fallback [Locale]
 * - does not use [getDefaultLocale] implicitly (one can define it explicitly as fallback [Locale] though)
 * - it does not return `java.util.Locale.ROOT`
 *
 * Further information can be found at [LocaleOrderDecider].
 */
class CoroutineBasedLocaleOrderDecider : LocaleOrderDecider {

    override fun determineOrder(primaryLocale: Locale, fallbackLocales: List<Locale>): Sequence<Locale> {
        val locales = mutableListOf<Locale>()
        forElementAndForEachIn(primaryLocale, fallbackLocales) { locale ->
            when (locale.language) {
                "zh" -> locales.specialCaseChinese(locale)
                else -> locales.normalCase(locale)
            }
        }
        return locales.asSequence()
    }

    private fun MutableList<Locale>.specialCaseChinese(locale: Locale) {
        val script = if (locale.script == null && locale.country != null) {
            when (locale.country) {
                "TW", "HK", "MO" -> "Hant"
                "CN", "SG" -> "Hans"
                else -> ""
            }
        } else {
            locale.script
        }
        normalCase(locale, script = script)
    }

    private fun MutableList<Locale>.normalCase(
        locale: Locale,
        language: String = locale.language,
        script: String? = locale.script,
        country: String? = locale.country,
        variant: String? = locale.variant
    ) {
        fallbackDueToVariant(language, script, country, variant)
        fallbackDueToCountry(language, script, country)
        fallbackDueToScript(language, script, country, variant)
        fallbackDueToLanguage(language)
    }

    private fun MutableList<Locale>.fallbackDueToVariant(
        language: String,
        script: String?,
        country: String?,
        variant: String?
    ) {
        if (variant != null) {
            var newVariant: String = variant
            do {
                val fallback = Locale(language, script, country, newVariant)
                add(fallback)
                newVariant = newVariant.substringBeforeLast('_', "")
            } while (newVariant.isNotEmpty())
        }
    }

    private fun MutableList<Locale>.fallbackDueToCountry(
        language: String,
        script: String?,
        country: String?
    ) {
        if (country != null) {
            val fallback = Locale(language, script, country, null)
            add(fallback)
        }
    }

    private fun MutableList<Locale>.fallbackDueToScript(
        language: String,
        script: String?,
        country: String?,
        variant: String?
    ) {
        if (script != null) {
            val fallback = Locale(language, script, null, null)
            add(fallback)

            // fallback variants without considering script
            fallbackDueToVariant(language, null, country, variant)

            // fallback country without considering script
            fallbackDueToCountry(language, null, country)
        }
    }

    private fun MutableList<Locale>.fallbackDueToLanguage(language: String) {
        val fallback = Locale(language)
        add(fallback)
    }
}
