package ch.tutteli.atrium.core.polyfills

actual class Locale(
    private val language: String,
    private val script: String,
    private val country: String
){
    actual fun getLanguage(): String = language
    actual fun getScript(): String = script
    actual fun getCountry(): String = country

}

/**
 * Returns [java.util.Locale.getDefault()]
 */
actual fun getLocaleDefault(): Locale {
    //TODO maybe come up with something else, such as the favourite lang in the browser (if available)
    return Locale("en", "", "GB")
}
