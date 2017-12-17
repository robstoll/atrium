package ch.tutteli.atrium.reporting.translating

import java.util.*
import kotlin.coroutines.experimental.SequenceBuilder
import kotlin.coroutines.experimental.buildSequence


/**
 * Responsible to determine in which order [Locale]s should be processed.
 *
 * Adopted from [ResourceBundle.Control.getCandidateLocales] and [ResourceBundle.Control.getFallbackLocale]
 * but allows to define more than one fallback [Locale] and does not use [Locale.getDefault] implicitly
 * (one can define it explicitly as fallback [Locale] though).
 *
 * There are further differences -- e.g., it does not return [Locale.ROOT] -- for more information,
 * have a look at [ILocaleOrderDecider].
 */
class LocaleOrderDecider : ILocaleOrderDecider {
    override fun determineOrder(primaryLocale: Locale, fallbackLocales: Array<out Locale>) = buildSequence {
        internalResolve(primaryLocale)
        fallbackLocales.forEach { internalResolve(it) }
    }

    private suspend fun SequenceBuilder<Locale>.internalResolve(locale: Locale) {
        when (locale.language) {
            "zh" -> specialCaseChinese(locale)
            else -> normalCase(locale)
        }
    }

    private suspend fun SequenceBuilder<Locale>.specialCaseChinese(locale: Locale) {
        val script = if (locale.script.isEmpty() && locale.country.isNotEmpty()) {
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

    private suspend fun SequenceBuilder<Locale>.normalCase(
        locale: Locale,
        language: String = locale.language,
        script: String = locale.script,
        country: String = locale.country,
        variant: String = locale.variant
    ) {
        fallbackDueToVariant(language, script, country, variant)
        fallbackDueToCountry(language, script, country)
        fallbackDueToScript(language, script, country, variant)
        fallbackDueToLanguage(language)
    }


    private suspend fun SequenceBuilder<Locale>.fallbackDueToVariant(
        language: String, script: String, country: String, variant: String
    ) {
        if (variant.isNotEmpty()) {
            var newVariant = variant
            do {
                val fallback = createLocale(language, script, country, newVariant)
                yield(fallback)
                newVariant = newVariant.substringBeforeLast('_', "")
            } while (newVariant.isNotEmpty())
        }
    }


    private suspend fun SequenceBuilder<Locale>.fallbackDueToCountry(
        language: String, script: String, country: String
    ) {
        if (country.isNotEmpty()) {
            val fallback = createLocale(language, script, country, "")
            yield(fallback)
        }
    }

    private suspend fun SequenceBuilder<Locale>.fallbackDueToScript(
        language: String, script: String, country: String, variant: String
    ) {
        if (script.isNotEmpty()) {
            val fallback = createLocale(language, script, "", "")
            yield(fallback)

            // fallback variants without considering script
            fallbackDueToVariant(language, "", country, variant)

            // fallback country without considering script
            fallbackDueToCountry(language, "", country)
        }
    }

    private suspend fun SequenceBuilder<Locale>.fallbackDueToLanguage(language: String) {
        if (language.isNotEmpty()) {
            val fallback = createLocale(language, "", "", "")
            yield(fallback)
        }
    }

    private fun createLocale(language: String, script: String, country: String, variant: String): Locale
        = Locale.Builder()
        .setLanguage(language)
        .setScript(script)
        .setRegion(country)
        .setVariant(variant)
        .build()
}
