package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.translating.Locale
import kotlin.String

actual fun String.format(locale: Locale, arg: Any, vararg otherArgs: Any): String {
    //TODO we have to come up with a solution here
    return this
}

actual fun String.format(arg: Any, vararg otherArgs: Any): String {
    //TODO we have to come up with a solution here
    return this
}
