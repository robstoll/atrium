package ch.tutteli.atrium.core.polyfills

import kotlin.String

actual fun String.Companion.format(locale: Locale, message: String, vararg arguments: Any): String {
    return java.lang.String.format(locale, message, *arguments)
}

actual fun String.Companion.format(message: String, vararg arguments: Any): String {
    return java.lang.String.format(message, *arguments)
}
