package ch.tutteli.atrium.reporting.translating

import java.util.*
import kotlin.coroutines.experimental.SequenceBuilder
import kotlin.coroutines.experimental.buildSequence


/**
 * Responsible to determine in which order [Locale]s should be processed.
 *
 * Adopted from [ResourceBundle.Control.getCandidateLocales].
 */
class LocaleOrderDecider : ILocaleOrderDecider {
    override fun determineOrder(locale: Locale, fallbackLocales: Array<out Locale>) = buildSequence {
        suspendedResolve(locale, fallbackLocales)
    }

    private suspend fun SequenceBuilder<Locale>.suspendedResolve(locale: Locale, fallbackLocales: Array<out Locale>) {
        internalResolve(locale)
        fallbackLocales.forEach {
            internalResolve(it)
        }
    }

    private suspend fun SequenceBuilder<Locale>.internalResolve(locale: Locale) {
        when (locale.language) {
            "no", "nb", "nn" -> specialCaseNorwegian(locale)
            "zh" -> specialCaseChinese(locale)
            else -> normalCase(locale)
        }
    }

    private suspend fun SequenceBuilder<Locale>.specialCaseNorwegian(locale: Locale) {
        var isNorwegianNynorsk = false
        var isNorwegianBokmal = false
        var variant = locale.variant

        if (locale.language == "no") {
            if (locale.country == "NO" && variant == "NY") {
                variant = ""
                isNorwegianNynorsk = true
            } else {
                isNorwegianBokmal = true
            }
        }

        if (locale.language == "nb" || isNorwegianBokmal) {
            normalCase(locale, language = "nb") {
                if (it.language.isNotEmpty()) {
                    val fallback = createLocale("no", it.script, it.country, it.variant)
                    yield(fallback)
                }
            }
        } else if (locale.language == "nn" || isNorwegianNynorsk) {
            normalCase(locale, language = "nn", variant = variant)
            yield(Locale("no", "NO", "NY"))
            yield(Locale("no", "NO", ""))
            yield(Locale("no", "", ""))
        }
    }

    private suspend fun SequenceBuilder<Locale>.specialCaseChinese(locale: Locale) {
        var script = locale.script
        var country = locale.country
        if (script.isEmpty() && country.isNotEmpty()) {
            // Supply script for users who want to use zh_Hans/zh_Hant as bundle names (recommended for Java7+)
            script = when (country) {
                "TW", "HK", "MO" -> "Hant"
                "CN", "SG" -> "Hans"
                else -> ""
            }
        } else if (script.isNotEmpty() && country.isEmpty()) {
            // Supply country(region) for users who still package Chinese bundles using old convension.
            country = when (script) {
                "Hans" -> "CN"
                "Hant" -> "TW"
                else -> ""
            }
        }
        normalCase(locale, script = script, country = country)
    }

    private suspend fun SequenceBuilder<Locale>.normalCase(
        locale: Locale,
        language: String = locale.language,
        script: String = locale.script,
        country: String = locale.country,
        variant: String = locale.variant,
        createAdditional: suspend SequenceBuilder<Locale>.(locale: Locale) -> Unit = {}
    ) {
        fallbackDueToVariant(language, script, country, variant, createAdditional)
        fallbackDueToCountry(language, script, country, createAdditional)
        fallbackDueToScript(language, script, country, variant, createAdditional)
        fallbackDueToLanguage(language, createAdditional)
        yield(Locale.ROOT)
    }


    private suspend fun SequenceBuilder<Locale>.fallbackDueToVariant(
        language: String, script: String, country: String, variant: String,
        createAdditional: suspend SequenceBuilder<Locale>.(locale: Locale) -> Unit
    ) {
        if (variant.isNotEmpty()) {
            var newVariant = variant
            do {
                val fallback = createLocale(language, script, country, newVariant)
                yieldAndCreateAdditional(fallback, createAdditional)
                newVariant = newVariant.substringBeforeLast('_', "")
            } while (newVariant.isNotEmpty())
        }
    }


    private suspend fun SequenceBuilder<Locale>.fallbackDueToCountry(
        language: String, script: String, country: String,
        createAdditional: suspend SequenceBuilder<Locale>.(locale: Locale) -> Unit
    ) {
        if (country.isNotEmpty()) {
            val fallback = createLocale(language, script, country, "")
            yieldAndCreateAdditional(fallback, createAdditional)
        }
    }

    private suspend fun SequenceBuilder<Locale>.fallbackDueToScript(
        language: String, script: String, country: String, variant: String,
        createAdditional: suspend SequenceBuilder<Locale>.(locale: Locale) -> Unit
    ) {
        if (script.isNotEmpty()) {
            val fallback = createLocale(language, script, "", "")
            yieldAndCreateAdditional(fallback, createAdditional)

            // fallback variants without considering script
            fallbackDueToVariant(language, "", country, variant, createAdditional)

            // fallback country without considering script
            fallbackDueToCountry(language, "", country, createAdditional)
        }
    }

    private suspend fun SequenceBuilder<Locale>.fallbackDueToLanguage(
        language: String, createAdditional: suspend SequenceBuilder<Locale>.(locale: Locale) -> Unit
    ) {
        if (language.isNotEmpty()) {
            val fallback = createLocale(language, "", "", "")
            yieldAndCreateAdditional(fallback, createAdditional)
        }
    }


    private suspend fun SequenceBuilder<Locale>.yieldAndCreateAdditional(
        fallback: Locale, createAdditional: suspend SequenceBuilder<Locale>.(locale: Locale) -> Unit
    ) {
        yield(fallback)
        createAdditional(fallback)
    }

    private fun createLocale(language: String, script: String, country: String, variant: String): Locale
        = Locale.Builder()
        .setLanguage(language)
        .setScript(script)
        .setRegion(country)
        .setVariant(variant)
        .build()
}
