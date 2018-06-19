package ch.tutteli.atrium.core.polyfills

expect class Locale {
    fun getLanguage(): String
    fun getScript(): String
    fun getCountry(): String
}

expect fun getLocaleDefault(): Locale
