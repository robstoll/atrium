package ch.tutteli.atrium.reporting.translating

interface LocaleProvider {
    fun getPrimaryLocale(): Locale
    fun getFallbackLocales(): List<Locale>
}

object UseDefaultLocaleAsPrimary : LocaleProvider {
    override fun getPrimaryLocale(): Locale = getDefaultLocale()
    override fun getFallbackLocales(): List<Locale> = emptyList()
}
