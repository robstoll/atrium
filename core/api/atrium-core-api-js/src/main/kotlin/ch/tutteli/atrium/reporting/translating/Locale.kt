package ch.tutteli.atrium.reporting.translating

actual fun getDefaultLocale(): Locale {
    //TODO maybe come up with something else, such as the favourite lang in the browser (if available)
    return Locale("en", "GB")
}
