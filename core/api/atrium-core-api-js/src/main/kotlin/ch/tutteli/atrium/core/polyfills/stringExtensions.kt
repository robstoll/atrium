package ch.tutteli.atrium.core.polyfills

import kotlin.String

actual fun String.Companion.format(locale: Locale, message: String, vararg arguments: Any): String {
    //TODO we have to come up with a solution here
    return message
}

actual fun String.Companion.format(message: String, vararg arguments: Any): String {
    //TODO we have to come up with a solution here
    return message
}
