package ch.tutteli.atrium.core.polyfills

expect fun String.Companion.format(locale: Locale, message: String, vararg arguments: Any): String

expect fun String.Companion.format(message: String, vararg arguments: Any): String
