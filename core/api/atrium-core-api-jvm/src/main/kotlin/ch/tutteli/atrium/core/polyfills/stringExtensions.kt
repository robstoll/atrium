package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.toJavaLocale

actual fun String.format(locale: Locale, arg: Any, vararg otherArgs: Any): String {
    return java.lang.String.format(locale.toJavaLocale(), this, arg, *otherArgs)
}

actual fun String.format(arg: Any, vararg otherArgs: Any): String {
    return java.lang.String.format(this, arg, *otherArgs)
}
