package ch.tutteli.atrium.core.polyfills

actual typealias Locale = java.util.Locale

/**
 * Returns [java.util.Locale.getDefault()]
 */
actual fun getLocaleDefault(): Locale {
    return java.util.Locale.getDefault()
}
