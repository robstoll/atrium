@file:Suppress(
    // TODO remove once https://youtrack.jetbrains.com/issue/KT-35343 is fixed
    "JAVA_MODULE_DOES_NOT_READ_UNNAMED_MODULE"
)

package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.toJavaLocale

actual fun String.format(locale: Locale, arg: Any, vararg otherArgs: Any): String {
    return java.lang.String.format(locale.toJavaLocale(), this, arg, *otherArgs)
}

actual fun String.format(arg: Any, vararg otherArgs: Any): String {
    return java.lang.String.format(this, arg, *otherArgs)
}
