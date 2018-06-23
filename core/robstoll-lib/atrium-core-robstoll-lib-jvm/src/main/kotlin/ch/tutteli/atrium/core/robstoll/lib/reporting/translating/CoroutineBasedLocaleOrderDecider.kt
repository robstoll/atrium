@file:Suppress("EXPERIMENTAL_FEATURE_WARNING")

package ch.tutteli.atrium.core.robstoll.lib.reporting.translating

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.LocaleOrderDecider
import ch.tutteli.kbox.forElementAndForEachIn
import java.util.*
import kotlin.coroutines.experimental.SequenceBuilder
import kotlin.coroutines.experimental.buildSequence

/**
 * Responsible to determine in which order [Locale]s should be processed.
 *
 * Adopted from [ResourceBundle.Control.getCandidateLocales] and [ResourceBundle.Control.getFallbackLocale]
 * but allows to define more than one fallback [Locale] and does not use [java.util.Locale.getDefault] implicitly
 * (one can define it explicitly as fallback [Locale] though).
 *
 * There are further differences -- e.g., it does not return [java.util.Locale.ROOT] -- for more information,
 * have a look at [LocaleOrderDecider].
 */
class CoroutineBasedLocaleOrderDecider : LocaleOrderDecider {

    override fun determineOrder(primaryLocale: Locale, fallbackLocales: List<Locale>): Sequence<Locale> {
        return buildSequence {
            forElementAndForEachIn(primaryLocale, fallbackLocales) { locale ->
                when (locale.language) {
                    "zh" -> specialCaseChinese(locale)
                    else -> normalCase(locale)
                }
            }
        }
    }

    private suspend fun SequenceBuilder<Locale>.specialCaseChinese(locale: Locale) {
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

    private suspend fun SequenceBuilder<Locale>.normalCase(
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


    private suspend fun SequenceBuilder<Locale>.fallbackDueToVariant(
        language: String,
        script: String?,
        country: String?,
        variant: String?
    ) {
        if (variant != null) {
            var newVariant: String = variant
            do {
                val fallback = Locale(language, script, country, newVariant)
                yield(fallback)
                newVariant = newVariant.substringBeforeLast('_', "")
            } while (newVariant.isNotEmpty())
        }
    }


    private suspend fun SequenceBuilder<Locale>.fallbackDueToCountry(
        language: String,
        script: String?,
        country: String?
    ) {
        if (country != null) {
            val fallback = Locale(language, script, country, null)
            yield(fallback)
        }
    }

    private suspend fun SequenceBuilder<Locale>.fallbackDueToScript(
        language: String,
        script: String?,
        country: String?,
        variant: String?
    ) {
        if (script != null) {
            val fallback = Locale(language, script, null, null)
            yield(fallback)

            // fallback variants without considering script
            fallbackDueToVariant(language, null, country, variant)

            // fallback country without considering script
            fallbackDueToCountry(language, null, country)
        }
    }

    private suspend fun SequenceBuilder<Locale>.fallbackDueToLanguage(language: String) {
        val fallback = Locale(language)
        yield(fallback)
    }
}
