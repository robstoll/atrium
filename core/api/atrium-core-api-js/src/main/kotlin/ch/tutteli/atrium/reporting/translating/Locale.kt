package ch.tutteli.atrium.reporting.translating

/**
 * Currently returns the locale for en_GB -- this might change in the future without notice.
 */
actual fun getDefaultLocale(): Locale {
    //TODO maybe come up with something else, such as the favourite lang in the browser (if available)
    return Locale("en", "GB")
}
